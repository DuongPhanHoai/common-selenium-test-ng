package com.david.test.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtils {
    public static String getFullStack(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
