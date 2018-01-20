package com.mysql.jdbc.log;

import com.mysql.jdbc.profiler.ProfilerEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySlf4JLogger extends StandardLogger {

    Logger logger = LoggerFactory.getLogger(getClass().getName());

    public MySlf4JLogger(String name) {
        super(name, false);
    }

    public MySlf4JLogger(String name, boolean logLocationInfo) {
        super(name, logLocationInfo);
    }

    DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");

    @Override
    protected void logInternal(int level, Object msg, Throwable exception) {
        if (msg instanceof ProfilerEvent) {
            ProfilerEvent evt = (ProfilerEvent) msg;
            String str = evt.getMessage();
            if (str != null) {
                logger.debug(str);
            }
        }
    }
}
