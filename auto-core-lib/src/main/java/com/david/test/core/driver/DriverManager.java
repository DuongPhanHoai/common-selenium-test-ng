package com.david.test.core.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.david.test.core.util.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DriverManager {
    RemoteWebDriver driver;

    public RemoteWebDriver getDriver() {
        return driver;
    }

    private WebDriverWait wait;

    public WebDriverWait getWait() {
        return wait;
    }

    public void setDefaultWait() {
        wait = new WebDriverWait(driver, TimeUtil.WAIT_SEC, TimeUtil.INTERVAL_MILLI_SEC);
        driver.manage().timeouts().implicitlyWait(TimeUtil.WAIT_SEC, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(TimeUtil.WAIT_SEC * 2, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(TimeUtil.WAIT_SEC, TimeUnit.SECONDS);
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
        driver.close();
        driver.quit();
    }
}
