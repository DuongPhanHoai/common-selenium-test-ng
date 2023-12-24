package com.david.test.core.util.string;

import java.util.Objects;

public class StringUtils {
    public static boolean isEmpty(String string) {
        if (Objects.nonNull(string) && string.length() > 0) return false;
        return true;
    }
}
