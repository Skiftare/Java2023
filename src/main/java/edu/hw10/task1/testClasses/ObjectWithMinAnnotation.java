package edu.hw10.task1.testClasses;

import edu.hw10.task1.Min;
import edu.hw10.task1.NotNull;

public class ObjectWithMinAnnotation {

    @Min(5)
    @NotNull
    private long minValue;

    public long getMinValue() {
        return minValue;
    }
}
