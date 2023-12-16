package edu.hw10.task1.testClasses;

import edu.hw10.task1.Max;
import edu.hw10.task1.Min;

public class ObjectWithMinAndMaxAnnotation {

    private int property;

    @Min(0)
    private int minValue;

    @Max(100)
    @Min(8)
    private int maxValue;

    public int getProperty() {
        return property;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
