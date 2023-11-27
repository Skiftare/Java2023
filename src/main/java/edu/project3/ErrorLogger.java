package edu.project3;

import edu.project3.utility.UtilityTableClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ErrorLogger {
    private final static Logger LOGGER = LogManager.getLogger();

    static void createLogError(String error){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StackTraceElement element = stackTraceElements[2];
        String logBuilder = element.getClassName() + "->" + element.getMethodName() +
            UtilityTableClass.CELL_SEPARATOR + error;
        LOGGER.info(logBuilder);

    }
}
