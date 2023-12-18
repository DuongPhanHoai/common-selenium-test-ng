package com.david.test.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.david.test.core.driver.WebDriverManager;
import com.david.test.core.util.LogUtils;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import lombok.extern.slf4j.Slf4j;

/** Verify first sample of BaseWebTest - init the drive with browser setting */
@Slf4j
public abstract class BaseWebTest {
    WebDriverManager webDriverManager;
    protected RemoteWebDriver driver;

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    protected void beforeMethod(@Optional("chrome") String browser) {
        initDriver(browser);
    }

    @AfterMethod(alwaysRun = true)
    protected synchronized void baseAfterMethod() {
        if (Objects.nonNull(webDriverManager))
            try {
                webDriverManager.end();
            } catch (NullPointerException npe) {
                log.debug("Driver was null, not running driver quit & remove");
            } catch (WebDriverException wde) {
                log.debug("Driver was not available, not running driver quit & remove");
            }
    }

    @AfterSuite(alwaysRun = true)
    protected synchronized void baseAfterSuite() {
        WebDriverManager.kill();
    }

    protected RemoteWebDriver initDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            try {
                return initChromeDriver();
            } catch (Exception e) {
                log.error(LogUtils.getFullStack(e));
            }
        } else log.error("Support only Chrome Browser");
        return null;
    }

    RemoteWebDriver initChromeDriver() {
        log.debug("Initializing Chrome driver");
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = initChomeOpt();
        LoggingPreferences logprefs = new LoggingPreferences();
        logprefs.enable(LogType.PERFORMANCE, Level.INFO);
        webDriverManager = new WebDriverManager(new ChromeDriver(chromeOptions));
        driver = webDriverManager.getDriver();
        driver.manage().window().maximize();
        return driver;
    }

    protected ChromeOptions initChomeOpt() {
        ChromeOptions cOpts = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("plugins.always_open_pdf_externally", true);
        cOpts.addArguments("--incognito");
        cOpts.addArguments("--no-sandbox");
        cOpts.setExperimentalOption("prefs", prefs);
        cOpts.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return cOpts;
    }
}
