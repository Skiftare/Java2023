package edu.hw10.task1.testClasses;

import edu.hw10.task1.Min;

public class ObjectWithMinAnnotation {

    @Min(5)
    private long minValue;

    public long getMinValue() {
        return minValue;
    }
}
