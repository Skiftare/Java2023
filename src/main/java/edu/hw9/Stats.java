package edu.hw9;

public class Stats {
    private final String metricName;
    private final double sum;
    private final double average;
    private final double min;
    private final double max;

    public Stats(String metricName, double sum, double average, double min, double max) {
        this.metricName = metricName;
        this.sum = sum;
        this.average = average;
        this.min = min;
        this.max = max;
    }

    public String getMetricName() {
        return metricName;
    }

    public double getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
