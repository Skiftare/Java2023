package edu.hw10.task1.testClasses;

import edu.hw10.task1.Max;

public class ObjectWithMaxAnnotation {

    @Max(100)
    private short maxValueShort;

    public short getMaxValueShort() {
        return maxValueShort;
    }
}
