package edu.hw9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class StatsCollector {
    private final Map<String, List<Double>> data;
    private final ReadWriteLock lock;

    StatsCollector() {
        this.data = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
    }

    public void push(String metricName, double[] values) {
        lock.writeLock().lock();
        try {
            List<Double> metricData = data.computeIfAbsent(metricName, k -> new ArrayList<>());
            for (double value : values) {
                metricData.add(value);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<Stats> stats() {
        lock.readLock().lock();
        try {
            List<Stats> statsList = new ArrayList<>();
            for (Map.Entry<String, List<Double>> entry : data.entrySet()) {
                String metricName = entry.getKey();
                List<Double> metricData = entry.getValue();

                double sum = 0;
                double min = Double.MAX_VALUE;
                double max = Double.MIN_VALUE;

                for (double value : metricData) {
                    sum += value;
                    min = Math.min(min, value);
                    max = Math.max(max, value);
                }

                double average = sum / metricData.size();

                statsList.add(new Stats(metricName, sum, average, min, max));
            }

            return statsList;
        } finally {
            lock.readLock().unlock();
        }
    }
}
