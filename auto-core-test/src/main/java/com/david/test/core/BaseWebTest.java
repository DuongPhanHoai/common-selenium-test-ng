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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import com.david.test.core.driver.WebDriverManager;
import com.david.test.core.util.LogUtils;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

/** Verify first sample of BaseWebTest - init the drive with browser setting */
public abstract class BaseWebTest {

    protected static final Logger LOG = LoggerFactory.getLogger(BaseWebTest.class);
    WebDriverManager webDriverManager;

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
                LOG.debug("Driver was null, not running driver quit & remove");
            } catch (WebDriverException wde) {
                LOG.debug("Driver was not available, not running driver quit & remove");
            }
    }

    @AfterSuite(alwaysRun = true)
    protected synchronized void baseAfterSuite() {
        WebDriverManager.kill();
    }

    protected void initDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            try {
                initChromeDriver();
            } catch (Exception e) {
                LOG.error(LogUtils.getFullStack(e));
            }
        } else LOG.error("Support only Chrome Browser");
    }

    void initChromeDriver() {
        LOG.debug("Initializing Chrome driver");
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = initChomeOpt();
        LoggingPreferences logprefs = new LoggingPreferences();
        logprefs.enable(LogType.PERFORMANCE, Level.INFO);
        webDriverManager = new WebDriverManager(new ChromeDriver(chromeOptions));
        webDriverManager.getDriver().manage().window().maximize();
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
