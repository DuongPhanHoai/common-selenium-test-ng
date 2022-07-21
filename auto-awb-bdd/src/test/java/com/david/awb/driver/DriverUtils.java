package com.david.awb.driver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.david.test.core.driver.WebDriverManager;
import com.david.test.core.util.LogUtils;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class DriverUtils {
    protected static final Logger LOG = LoggerFactory.getLogger(DriverUtils.class);
    static String downloadFilepath = System.getProperty("user.dir") + File.separator + "Download";

    public static String getDownloadDir() {
        return downloadFilepath;
    }

    public static WebDriverManager getNewDriver(String browser) {
        WebDriverManager driverManager = null;
        if (browser.equalsIgnoreCase("chrome")) {
            try {
                driverManager = initChromeDriver();
            } catch (Exception e) {
                LOG.error(LogUtils.getFullStack(e));
            }
        } else LOG.error("Support only Chrome Browser");
        return driverManager;
    }

    static WebDriverManager initChromeDriver() {

        LOG.debug("Initializing Chrome driver");
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = initChomeOpt();
        DesiredCapabilities dCap = DesiredCapabilities.chrome();
        dCap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        dCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        LoggingPreferences logprefs = new LoggingPreferences();
        logprefs.enable(LogType.PERFORMANCE, Level.INFO);
        dCap.setCapability(CapabilityType.LOGGING_PREFS, logprefs);
        RemoteWebDriver webDriver = new ChromeDriver(dCap);
        webDriver.manage().window().maximize();
        return new WebDriverManager(webDriver);
    }

    static ChromeOptions initChomeOpt() {
        ChromeOptions cOpts = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFilepath);
        prefs.put("plugins.always_open_pdf_externally", true);
        cOpts.addArguments("--incognito");
        cOpts.addArguments("--no-sandbox");
        cOpts.setExperimentalOption("prefs", prefs);
        cOpts.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return cOpts;
    }
}
