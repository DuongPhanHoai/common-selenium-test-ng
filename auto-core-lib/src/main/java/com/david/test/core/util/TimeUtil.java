package com.david.test.core.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeUtil {
    public static final int WAIT_SEC = 30;
    public static final int INTERVAL_MILLI_SEC = 500;

    public static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            log.debug("Sleep exception: ", e);
            Thread.currentThread().interrupt();
        }
    }

    public static void sleepMilliseconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.debug("Sleep exception: ", e);
            Thread.currentThread().interrupt();
        }
    }
}
