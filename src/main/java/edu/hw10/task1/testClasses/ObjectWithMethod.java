package edu.hw10.task1.testClasses;

import org.jetbrains.annotations.NotNull;

public class ObjectWithMethod {
    private String justN;

    public static @NotNull ObjectWithMethod create(String incomeN) {
        ObjectWithMethod obj = new ObjectWithMethod();
        obj.justN = incomeN;
        return obj;
    }

    public String getJustN() {
        return justN;
    }
}
