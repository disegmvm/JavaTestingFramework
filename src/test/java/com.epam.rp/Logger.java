package com.epam.rp;

import org.apache.logging.log4j.LogManager;

public class Logger {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    public static void logDebugMessage(String message) {
        logger.debug(message);
    }

    public static void logErrorMessage(String message) {
        logger.error(message);
    }
}
