package com.david.test.core.driver;

import com.david.test.core.BaseWebTest;
import com.david.test.core.util.LogUtils;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WebDriverManager extends DriverManager {
    protected static final Logger LOG = LoggerFactory.getLogger(WebDriverManager.class);

    public WebDriverManager(RemoteWebDriver driver) {
        super(driver);
    }

    public static void kill() {
        try {
            Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
        }
        catch (IOException e) {
            LOG.error(LogUtils.getFullStack(e));
        }
    }
}
