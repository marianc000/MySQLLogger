package com.mysql.jdbc.log;

import java.util.Date;

import com.mysql.jdbc.Util;
import com.mysql.jdbc.profiler.ProfilerEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Provides logging facilities for those platforms that don't have built-in
 * facilities. Simply logs messages to STDERR.
 */
// url=jdbc:mysql://sarcdwh-dev/cbioportal?user=cbio_user&password=Tomcat0-&zeroDateTimeBehavior=convertToNull&useSSL=false&profileSQL=true&logger=com.mysql.jdbc.log.MyStandardLogger
public class MyStandardLogger implements Log {

    private static final int FATAL = 0;

    private static final int ERROR = 1;

    private static final int WARN = 2;

    private static final int INFO = 3;

    private static final int DEBUG = 4;

    private static final int TRACE = 5;

    private static StringBuffer bufferedLog = null;

    private boolean logLocationInfo = true;

    /**
     * Creates a new StandardLogger object.
     *
     * @param name the name of the configuration to use -- ignored
     */
    public MyStandardLogger(String name) {
        this(name, false);
    }

    /**
     * @param name
     * @param logLocationInfo
     */
    public MyStandardLogger(String name, boolean logLocationInfo) {
        this.logLocationInfo = logLocationInfo;
    }

    public static void startLoggingToBuffer() {
        bufferedLog = new StringBuffer();
    }

    public static void dropBuffer() {
        bufferedLog = null;
    }

    public static Appendable getBuffer() {
        return bufferedLog;
    }

    /**
     * @see com.mysql.jdbc.log.Log#isDebugEnabled()
     */
    public boolean isDebugEnabled() {
        return true;
    }

    /**
     * @see com.mysql.jdbc.log.Log#isErrorEnabled()
     */
    public boolean isErrorEnabled() {
        return true;
    }

    /**
     * @see com.mysql.jdbc.log.Log#isFatalEnabled()
     */
    public boolean isFatalEnabled() {
        return true;
    }

    /**
     * @see com.mysql.jdbc.log.Log#isInfoEnabled()
     */
    public boolean isInfoEnabled() {
        return true;
    }

    /**
     * @see com.mysql.jdbc.log.Log#isTraceEnabled()
     */
    public boolean isTraceEnabled() {
        return true;
    }

    /**
     * @see com.mysql.jdbc.log.Log#isWarnEnabled()
     */
    public boolean isWarnEnabled() {
        return true;
    }

    /**
     * Logs the given message instance using the 'debug' level
     *
     * @param message the message to log
     */
    public void logDebug(Object message) {
        logInternal(DEBUG, message, null);
    }

    /**
     * Logs the given message and Throwable at the 'debug' level.
     *
     * @param message the message to log
     * @param exception the throwable to log (may be null)
     */
    public void logDebug(Object message, Throwable exception) {
        logInternal(DEBUG, message, exception);
    }

    /**
     * Logs the given message instance using the 'error' level
     *
     * @param message the message to log
     */
    public void logError(Object message) {
        logInternal(ERROR, message, null);
    }

    /**
     * Logs the given message and Throwable at the 'error' level.
     *
     * @param message the message to log
     * @param exception the throwable to log (may be null)
     */
    public void logError(Object message, Throwable exception) {
        logInternal(ERROR, message, exception);
    }

    /**
     * Logs the given message instance using the 'fatal' level
     *
     * @param message the message to log
     */
    public void logFatal(Object message) {
        logInternal(FATAL, message, null);
    }

    /**
     * Logs the given message and Throwable at the 'fatal' level.
     *
     * @param message the message to log
     * @param exception the throwable to log (may be null)
     */
    public void logFatal(Object message, Throwable exception) {
        logInternal(FATAL, message, exception);
    }

    /**
     * Logs the given message instance using the 'info' level
     *
     * @param message the message to log
     */
    public void logInfo(Object message) {
        logInternal(INFO, message, null);
    }

    /**
     * Logs the given message and Throwable at the 'info' level.
     *
     * @param message the message to log
     * @param exception the throwable to log (may be null)
     */
    public void logInfo(Object message, Throwable exception) {
        logInternal(INFO, message, exception);
    }

    /**
     * Logs the given message instance using the 'trace' level
     *
     * @param message the message to log
     */
    public void logTrace(Object message) {
        logInternal(TRACE, message, null);
    }

    /**
     * Logs the given message and Throwable at the 'trace' level.
     *
     * @param message the message to log
     * @param exception the throwable to log (may be null)
     */
    public void logTrace(Object message, Throwable exception) {
        logInternal(TRACE, message, exception);
    }

    /**
     * Logs the given message instance using the 'warn' level
     *
     * @param message the message to log
     */
    public void logWarn(Object message) {
        logInternal(WARN, message, null);
    }

    /**
     * Logs the given message and Throwable at the 'warn' level.
     *
     * @param message the message to log
     * @param exception the throwable to log (may be null)
     */
    public void logWarn(Object message, Throwable exception) {
        logInternal(WARN, message, exception);
    }
    DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");

    protected void logInternal(int level, Object msg, Throwable exception) {
        StringBuilder msgBuf = new StringBuilder();

        if (msg instanceof ProfilerEvent) {
            ProfilerEvent evt = (ProfilerEvent) msg;
            String evtMessage = evt.getMessage();

            if (evtMessage != null) {
                msgBuf.append(df.format(new Date()));
                msgBuf.append("\t");
                msgBuf.append(evtMessage);
            }
            String messageAsString = msgBuf.toString();
            System.out.println(messageAsString);
        }
    }

}
