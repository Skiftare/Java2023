package edu.hw9;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class ErrorLogger {
    private final static Logger LOGGER = LogManager.getLogger();

    static void createLogError(String error) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StackTraceElement element = stackTraceElements[2];
        String logBuilder = element.getClassName() + "->"
            + element.getMethodName()
            + "| "
            + error;
        LOGGER.info(logBuilder);

    }
}
