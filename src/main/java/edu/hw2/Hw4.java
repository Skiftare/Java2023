package edu.hw2;

import org.jetbrains.annotations.NotNull;


@SuppressWarnings("HideUtilityClassConstructor")
public class Hw4 {
    static @NotNull String callingInfo() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String message = "";

        StackTraceElement element = stackTraceElements[2];
        String className = element.getClassName();
        String methodName = element.getMethodName();
        message = className + "->" + methodName;

        return message;
    }
}
