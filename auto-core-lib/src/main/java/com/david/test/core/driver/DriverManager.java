package com.david.test.core.driver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class DriverManager {
    RemoteWebDriver driver;

    public RemoteWebDriver getDriver() {
        return driver;
    }

    private static final int WAIT_SEC = 30;
    private static final int INTERVAL_MILLI_SEC = 500;

    private WebDriverWait wait;

    public WebDriverWait getWait() {
        return wait;
    }

    public void setDefaultWait() {
        wait = new WebDriverWait(driver, WAIT_SEC, INTERVAL_MILLI_SEC);
        driver.manage().timeouts().implicitlyWait(WAIT_SEC, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(WAIT_SEC * 2, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(WAIT_SEC, TimeUnit.SECONDS);
    }

    public DriverManager() {
        setDefaultWait();
    }

    public DriverManager(Capabilities desiredCapabilities) {
        driver = new RemoteWebDriver(desiredCapabilities);
        setDefaultWait();
    }

    public DriverManager(RemoteWebDriver driver) {
        this.driver = driver;
        setDefaultWait();
    }

    public void end() {
        driver.quit();
    }
}
