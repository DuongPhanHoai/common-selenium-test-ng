package com.david.awb.page;

import org.openqa.selenium.support.FindBy;

import com.david.test.core.base.Page;
import com.david.test.core.driver.DriverManager;
import com.david.test.core.driver.WebDriverManager;
import com.david.test.core.dto.ServerInfo;
import com.david.test.core.element.WElement;
import com.david.test.core.util.LogUtils;

public class LoginPage extends Page {

    public LoginPage(WebDriverManager webDriverManager) {
        super((DriverManager) webDriverManager);
    }

    @FindBy(xpath = "//input[@id='email']")
    WElement inputEmail;

    @FindBy(xpath = "//input[@id='password']")
    WElement inputPassword;

    @FindBy(xpath = "//*[contains(text(),'Login')]/ancestor::button")
    WElement btnLogin;

    public void login(ServerInfo serverInfo) {
        try {
            driverManager.getDriver().get(serverInfo.getBaseURL() + "users/signin");
            if (btnLogin.isDisplayed()) LOG.info("Successfully opened Google");
            inputEmail.sendKeys(serverInfo.getLoginEmail());
            inputPassword.sendKeys(serverInfo.getLoginPwd());
            btnLogin.click();
        } catch (Exception e) {
            LOG.debug("Exception on open GOOGLE: {}", LogUtils.getFullStack(e));
        }
    }
}
