package com.david.test.core.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.FindBy;

public class Reflect {

    public static boolean isFinBy(Field field) {
        return field.isAnnotationPresent(FindBy.class)
                || field.getType().isAnnotationPresent(FindBy.class);
    }

    public static Object getValue(Field field, Object obj) {
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Check if the right type
     *
     * @param actualType Class
     * @param expectedType Class
     * @param isClass true: check class matching, false to check interface
     * @return boolean
     */
    public static boolean isType(Class<?> actualType, Class<?> expectedType, boolean isClass) {
        if (actualType == null || expectedType == null || actualType == Object.class) return false;
        if (actualType == expectedType) return true;
        if (!isClass) { // Check interface
            List<Class> interfaces = Arrays.asList(actualType.getInterfaces());
            for (Class inf : interfaces) {
                if (isType(inf, expectedType, false)) return true;
            }
        }
        Class<?> scanType = actualType;
        while (scanType != null && scanType != Object.class)
            if (scanType == expectedType) return true;
            else scanType = scanType.getSuperclass();
        return false;
    }
}
