package com.david.test.core.element;

import org.openqa.selenium.*;

import com.david.test.core.driver.DriverManager;

public class Element {
    protected DriverManager driverManager;
    protected String name;

    protected By by;

    public void setBy(By by) {
        this.by = by;
    }

    public Element(DriverManager driverManager, String name) {
        this.driverManager = driverManager;
        this.name = name;
    }
}
