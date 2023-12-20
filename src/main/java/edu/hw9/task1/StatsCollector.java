package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StatsCollector {
    private final ConcurrentMap<String, List<Double>> data;

    public StatsCollector() {
        this.data = new ConcurrentHashMap<>();
    }

    public synchronized void push(String metricName, double[] values) {

        List<Double> metricData = data.computeIfAbsent(metricName, k -> new ArrayList<>());
        for (double value : values) {
            metricData.add(value);
        }

    }

    public List<Stats> stats() {
        List<Stats> statsList = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : data.entrySet()) {
            String metricName = entry.getKey();
            List<Double> metricData = entry.getValue();

            double sum = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;

            for (Double value : metricData) {
                sum += value;
                min = Math.min(min, value);
                max = Math.max(max, value);
            }

            double average = sum / metricData.size();

            statsList.add(new Stats(metricName, sum, average, min, max));
        }

        return statsList;

    }
}
