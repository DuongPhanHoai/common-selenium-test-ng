package com.dk.test.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StringUtil {
    public static String getFullStack(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
