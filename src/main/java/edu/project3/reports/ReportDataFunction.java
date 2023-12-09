package edu.project3.reports;

import java.util.Map;

@FunctionalInterface
public interface ReportDataFunction {
    void generate(Report report, Map<String, Integer> data);
}
