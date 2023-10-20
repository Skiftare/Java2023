package edu.hw2;

import org.jetbrains.annotations.NotNull;

public class Hw4 {
    static String callingInfo(){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String message = "";
        if(stackTraceElements.length >= 3) {
            StackTraceElement element = stackTraceElements[2];
            String className = element.getClassName();
            String methodName = element.getMethodName();
            message = className + "->" + methodName;
        }

        return message;
    }
}
