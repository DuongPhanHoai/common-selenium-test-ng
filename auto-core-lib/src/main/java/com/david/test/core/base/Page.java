package com.david.test.core.base;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.david.test.core.element.ElementFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/** Page model, loading element on the construction */
public class Page {
    protected RemoteWebDriver driver;

    public Page(RemoteWebDriver driver) {
        this.driver = driver;
        ElementFactory.loadElements(driver, this);
    }
}
