package com.dk.test.core.page;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dk.test.core.driver.Driver;
import com.dk.test.core.element.Element;
import com.dk.test.core.element.ElementList;
import com.dk.test.core.util.Reflect;
import com.dk.test.core.util.StringUtil;

public class PageFactory {
    protected static final Logger LOG = LoggerFactory.getLogger(PageFactory.class);

    public static void loadElements(Driver driver, Object pageElement) {
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

    private static void loadElement(Driver driver, Object pageElement, Field field) {
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
            LOG.error(StringUtil.getFullStack(e));
        }
    }

    /**
     * init Test Element
     *
     * @param driver the driver to init
     * @param field has to be Element or inherit from Element
     * @return Object with the Field class
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static Object createElement(Driver driver, Field field)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException,
                    IllegalAccessException {
        Constructor constructor =
                field.getType().getConstructor(new Class[] {Driver.class, String.class});
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
