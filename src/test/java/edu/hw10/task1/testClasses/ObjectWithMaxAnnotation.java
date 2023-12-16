package edu.hw10.task1.testClasses;

import edu.hw10.task1.Max;

public class ObjectWithMaxAnnotation {

    private int property;
    @Max (100)
    private int maxValue;

    public int getProperty() {
        return property;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
