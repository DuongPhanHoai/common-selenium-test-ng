package com.david.test.core.element;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Element {
    protected RemoteWebDriver driver;
    protected String name;

    protected By by;

    public void setBy(By by) {
        this.by = by;
    }

    public Element(RemoteWebDriver driver, String name) {
        this.driver = driver;
        this.name = name;
    }
}
