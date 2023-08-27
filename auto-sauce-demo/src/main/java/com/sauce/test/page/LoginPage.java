package com.sauce.test.page;

import org.openqa.selenium.support.FindBy;

import com.david.test.core.base.Page;
import com.david.test.core.driver.WebDriverManager;
import com.david.test.core.element.WElement;
import com.sauce.test.data.ServerInfo;

/** Very first coding sample of POM */
public class LoginPage extends Page {

    public LoginPage(WebDriverManager driverManager) {
        super(driverManager);
    }

    @FindBy(id = "user-name")
    WElement inputUserName;

    @FindBy(id = "password")
    WElement inputPassword;

    @FindBy(id = "login-button")
    WElement loginButton;

    public void login(ServerInfo serverInfo) {
        driverManager.getDriver().get(serverInfo.getBaseURL());
        if (loginButton.isDisplayed()) LOG.info("Successfully opened Sauce");
        inputUserName.sendKeys(serverInfo.getStandardUsername());
        inputPassword.sendKeys(serverInfo.getStandardUserPwd());
        loginButton.click();
    }
}
