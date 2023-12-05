package edu.hw9;

public class Stats {
    private String metricName;
    private double sum;
    private double average;
    private double min;
    private double max;

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
