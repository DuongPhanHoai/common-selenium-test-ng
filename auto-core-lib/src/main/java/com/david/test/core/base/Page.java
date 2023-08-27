package com.david.test.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.david.test.core.driver.DriverManager;
import com.david.test.core.element.ElementFactory;

/** Page model, loading element on the construction */
public class Page {
    protected static final Logger LOG = LoggerFactory.getLogger(Page.class);
    protected DriverManager driverManager;

    public Page(DriverManager driverManager) {
        this.driverManager = driverManager;
        ElementFactory.loadElements(driverManager, this);
    }
}
