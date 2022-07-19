package com.david.test.core.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Driver extends RemoteWebDriver {
    private static final int WAIT_SEC = 30;
    private static final int INTERVAL_MILLI_SEC = 500;

    private WebDriverWait wait;

    public WebDriverWait getWait() {
        return wait;
    }

    public void setDefaultWait() {
        wait = new WebDriverWait(this, WAIT_SEC, INTERVAL_MILLI_SEC);
        manage().timeouts().implicitlyWait(WAIT_SEC, TimeUnit.SECONDS);
        manage().timeouts().pageLoadTimeout(WAIT_SEC * 2, TimeUnit.SECONDS);
        manage().timeouts().setScriptTimeout(WAIT_SEC, TimeUnit.SECONDS);
    }

    public Driver() {
        setDefaultWait();
    }

    public Driver(Capabilities desiredCapabilities) {
        super(desiredCapabilities);
        setDefaultWait();
    }
}
