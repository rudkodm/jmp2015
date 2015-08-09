package com.rm.eholiday;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

/**
 * User: Roman_Makarevich
 * Date: 12/12/13
 * Time: 2:06 PM
 */
public class Log {

    private static final int PAD_COMPONENT = 8;
    private static final int PAD_LEVEL = 7;

    public static enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private static Log log = new Log("System");

    private static Level level = Level.INFO;
    static {
        String strLevel = System.getProperty("logLevel");
        if (strLevel != null && !strLevel.isEmpty()) {
            try {
                level = Level.valueOf(strLevel);
            } catch (IllegalArgumentException e) {
                log.error("Invalid log level: " + strLevel, e);
            }
        }
    }

    private String component;

    public static Log newLog(String name) {
        return new Log(name);
    }

    public static Level getLevel() {
        return level;
    }

    private Log(String component) {
        this.component = component;
    }

    public String getComponent() {
        return component;
    }

    public boolean isLoggable(Level level) {
        return level.compareTo(Log.level) >= 0;
    }

    public static String padRight(String str, int pad) {
        return String.format("%1$-" + pad + "s", str);
    }

    public void log(Level level, Object message, Throwable t) {
        if (!isLoggable(level)) {
            return;
        }

        StringBuilder logMessage = new StringBuilder();
        logMessage.append("[").append(padRight(level.toString(), PAD_LEVEL)).append("] ");
        logMessage.append("[").append(padRight(component, PAD_COMPONENT)).append("] ");
        logMessage.append(new Date()).append(" - ").append(message);

        if (t != null) {
            Writer stackTrace = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stackTrace);
            t.printStackTrace(printWriter);
            logMessage.append("\n").append(stackTrace);
        }

        System.out.println(logMessage);
    }

    public boolean isDebug() {
        return isLoggable(Level.DEBUG);
    }

    public void debug(Object message, Throwable t) {
        log(Level.DEBUG, message, t);
    }

    public void debug(Object message) {
        debug(message, null);
    }

    public boolean isWarning() {
        return isLoggable(Level.WARNING);
    }

    public void warning(Object message, Throwable t) {
        log(Level.WARNING, message, t);
    }

    public void warning(Object message) {
        warning(message, null);
    }

    public boolean isInfo() {
        return isLoggable(Level.INFO);
    }

    public void info(Object message, Throwable t) {
        log(Level.INFO, message, t);
    }

    public void info(Object message) {
        info(message, null);
    }

    public boolean isError() {
        return isLoggable(Level.ERROR);
    }

    public void error(Object message, Throwable t) {
        log(Level.ERROR, message, t);
    }

    public void error(Object message) {
        error(message, null);
    }

}
