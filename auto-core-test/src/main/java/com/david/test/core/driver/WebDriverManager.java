package com.david.test.core.driver;

import java.io.IOException;
import java.util.LinkedList;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.david.test.core.util.LogUtils;
import com.david.test.core.util.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebDriverManager extends DriverManager {

    public WebDriverManager(RemoteWebDriver driver) {
        super(driver);
    }

    public void switchToTabIndex(int index) {
        // find tab index in 15 sseconds
        for (int i = 0; i < 15; i++) {
            LinkedList<String> handles = new LinkedList<>(getDriver().getWindowHandles());
            int tabsNum = handles.size();
            if (tabsNum > index) {
                getDriver().switchTo().window(handles.get(index));
                break;
            } else TimeUtil.sleep(1);
        }
    }

    public static void kill() {
        try {
            Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
        } catch (IOException e) {
            log.error(LogUtils.getFullStack(e));
        }
    }
}
