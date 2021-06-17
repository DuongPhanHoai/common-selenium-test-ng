package com.dk.test.core.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dk.test.core.driver.Driver;

public class PageElement {
    protected static final Logger LOG = LoggerFactory.getLogger(PageElement.class);
    protected Driver driver;

    public PageElement(Driver driver) {
        this.driver = driver;
        PageFactory.loadElements(driver, this);
    }
}
