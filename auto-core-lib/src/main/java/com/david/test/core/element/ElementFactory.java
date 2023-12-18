package com.david.test.core.element;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.david.test.core.util.LogUtils;
import com.david.test.core.util.Reflect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ElementFactory {

    public static void loadElements(RemoteWebDriver driver, Object pageElement) {
        Class<?> type = pageElement.getClass();
        List<Field> fields = new ArrayList<>();
        while (type != null) {
            fields.addAll(Arrays.asList(type.getDeclaredFields()));
            type = type.getSuperclass();
        }
        for (Field field : fields) {
            loadElement(driver, pageElement, field);
        }
    }

    private static void loadElement(RemoteWebDriver driver, Object pageElement, Field field) {
        try {
            if (!Reflect.isFinBy(field)) {
                return;
            }
            Object instance = Reflect.getValue(field, pageElement);
            if (instance == null) {
                instance = createElement(driver, field);
            }
            if (instance != null) {
                Class<?> type = instance.getClass();
                if (Reflect.isType(type, Element.class, true)) {
                    Element dkE = (Element) instance;
                    By locator = getLocatorFromField(field);
                    if (locator != null) dkE.setBy(locator);
                }
                field.set(pageElement, instance);
            }
        } catch (Exception e) {
            log.error(LogUtils.getFullStack(e));
        }
    }

    /** init Test Element */
    private static Object createElement(RemoteWebDriver driver, Field field)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException,
                    IllegalAccessException {
        Constructor constructor =
                field.getType().getConstructor(new Class[] {RemoteWebDriver.class, String.class});
        if (Reflect.isType(field.getType(), WebElement.class, false)) {
            return constructor.newInstance(driver, field.getName());
        }
        if (Reflect.isType(field.getType(), ElementList.class, true)
                || Reflect.isType(field.getType(), List.class, false)) {
            return new Element(driver, field.getName());
        } else return null;
    }

    // Process with locator by
    private static By getLocatorFromField(Field field) {
        if (Reflect.isFinBy(field)) return getBy(field.getAnnotation(FindBy.class));
        return null;
    }

    private static By getBy(FindBy findBy) {
        if (findBy == null) return null;
        if (!findBy.id().isEmpty()) return By.id(findBy.id());
        if (!findBy.className().isEmpty()) return By.className(findBy.className());
        if (!findBy.xpath().isEmpty()) return By.xpath(findBy.xpath());
        if (!findBy.css().isEmpty()) return By.cssSelector(findBy.css());
        if (!findBy.linkText().isEmpty()) return By.linkText(findBy.linkText());
        if (!findBy.name().isEmpty()) return By.name(findBy.name());
        if (!findBy.partialLinkText().isEmpty())
            return By.partialLinkText(findBy.partialLinkText());
        if (!findBy.tagName().isEmpty()) return By.tagName(findBy.tagName());
        return null;
    }
}
