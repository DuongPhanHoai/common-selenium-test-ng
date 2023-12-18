package com.david.aw.page;

import java.util.Objects;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.david.aw.data.ServerInfo;
import com.david.test.core.base.Page;
import com.david.test.core.element.WElement;

import lombok.extern.slf4j.Slf4j;

/** After access the home address */
@Slf4j
public class HomePage extends Page {

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        if (Objects.isNull(driver)) {
            log.error("driver is null");
        }
    }

    @FindBy(css = ".hamburger-button.icon-hamburger")
    WElement topRightMenu;

    @FindBy(xpath = "//div[@class='header-menu']//a[contains(text(),'Daily')]")
    WElement topRightMenuItem_Daily;

    public void launchHome(ServerInfo serverInfo) {
        if (Objects.isNull(driver)) {
            log.error("driver is null");
        } else {
            log.error("driver is not null, ready for {}", serverInfo.getBaseURL());
        }
        driver.get(serverInfo.getBaseURL());
        log.info("Successfully launching page");
    }

    public void openDayMenu() {
        topRightMenu.click();
        topRightMenuItem_Daily.click();
    }
}
