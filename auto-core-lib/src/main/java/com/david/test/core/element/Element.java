package com.david.test.core.element;

import org.openqa.selenium.*;

import com.david.test.core.driver.Driver;

public class Element {
    protected Driver driver;
    protected String name;

    protected By by;

    public void setBy(By by) {
        this.by = by;
    }

    public Element(Driver driver, String name) {
        this.driver = driver;
        this.name = name;
    }
}
