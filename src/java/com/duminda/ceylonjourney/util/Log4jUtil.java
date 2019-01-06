package com.duminda.ceylonjourney.util;

import org.apache.log4j.Logger;

/**
 * This Log4Util class is a log management utility. It provides static methods to create log entries.
 * @author Duminda
 */
public class Log4jUtil {

    private static org.apache.log4j.Logger log = Logger.getLogger("errors.log");

    public Log4jUtil() {
    }

    public static void logErrorMessage(Object message) {
        log.error(message);
    }

    public static void logWarnMessage(Object message) {
        log.warn(message);
    }

    public static void logFatalMessage(Object message) {

        log.fatal(message);
    }

    public static void logInfoMessage(Object message) {
        log.info(message);
    }
}
