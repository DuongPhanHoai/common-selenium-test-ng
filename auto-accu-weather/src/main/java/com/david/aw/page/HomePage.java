package com.david.aw.page;

import org.openqa.selenium.support.FindBy;

import com.david.aw.data.ServerInfo;
import com.david.test.core.base.Page;
import com.david.test.core.driver.DriverManager;
import com.david.test.core.element.WElement;

/** After access the home address */
public class HomePage extends Page {
    public HomePage(DriverManager driverManager) {
        super(driverManager);
    }

    @FindBy(css = ".hamburger-button.icon-hamburger")
    WElement topRightMenu;

    @FindBy(xpath = "//div[@class='header-menu']//a[contains(text(),'Daily')]")
    WElement topRightMenuItem_Daily;

    public void launchHome(ServerInfo serverInfo) {
        driverManager.getDriver().get(serverInfo.getBaseURL());
        LOG.info("Successfully launching page");
    }

    public void openDayMenu() {
        topRightMenu.click();
        topRightMenuItem_Daily.click();
    }
}
