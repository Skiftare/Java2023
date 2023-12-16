package edu.hw10.task1.testClasses;

import edu.hw10.task1.Min;

public class ObjectWithMinAnnotation {

    private int property;
    @Min(5)
    private int minValue;

    public int getProperty() {
        return property;
    }

    public int getMinValue() {
        return minValue;
    }
}
