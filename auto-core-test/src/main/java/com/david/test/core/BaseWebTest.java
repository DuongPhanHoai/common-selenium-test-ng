package com.david.test.core;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.david.test.core.driver.Driver;
import com.david.test.core.driver.WebDriver;

/** Verify first sample of BaseWebTest - init the drive with browser setting */
public abstract class BaseWebTest {
    Driver driver;

    public Driver getDriver() {
        return driver;
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    protected void beforeMethod(@Optional("chrome") String browser) {
        initDriver(browser);
    }

    protected void initDriver(String browser) {
        ChromeOptions chromeOptions = initChomeOpt();
        DesiredCapabilities dCap = DesiredCapabilities.chrome();
        dCap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        dCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        LoggingPreferences logprefs = new LoggingPreferences();
        logprefs.enable(LogType.PERFORMANCE, Level.INFO);
        dCap.setCapability(CapabilityType.LOGGING_PREFS, logprefs);
        driver = new WebDriver(dCap);
    }

    protected ChromeOptions initChomeOpt() {
        ChromeOptions cOpts = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        cOpts.addArguments("--incognito");
        cOpts.addArguments("--no-sandbox");
        cOpts.setExperimentalOption("prefs", prefs);
        cOpts.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return cOpts;
    }
}
