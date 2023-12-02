package edu.project4.helperCore;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class ErrorLogger {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String SEPARATOR = " -> ";

    static void createLogError(String error) {

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StackTraceElement element = stackTraceElements[2];
        String logBuilder = element.getClassName()
            + SEPARATOR
            + element.getMethodName()
            + SEPARATOR
            + error;
        LOGGER.info(logBuilder);

    }
}
