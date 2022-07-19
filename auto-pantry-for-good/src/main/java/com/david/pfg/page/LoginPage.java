package com.david.pfg.page;

import org.openqa.selenium.support.FindBy;

import com.david.test.core.base.Page;
import com.david.test.core.driver.DriverManager;
import com.david.test.core.element.WElement;
import com.david.test.core.util.LogUtils;

/** Very first coding sample of POM */
public class LoginPage extends Page {

    static final String PFG_LOGIN_ADDRESS = "http://localhost:8080/users/signin";

    public LoginPage(DriverManager driverManager) {
        super(driverManager);
    }

    @FindBy(name = "email")
    WElement txtEmail;

    @FindBy(name = "password")
    WElement txtPwd;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-flat']")
    WElement btnLogin;

    @FindBy(linkText = "Customers")
    WElement lnkCustomers;

    @FindBy(xpath = "//*[contains(text(),'Unknown user or invalid password')]")
    WElement iLoginFailMessage;

    public void login(String userName, String passWord) {
        try {
            driverManager.getDriver().get(PFG_LOGIN_ADDRESS);
            if (btnLogin.isDisplayed()) LOG.info("Successfully opened Google");
            txtEmail.sendKeys(userName);
            txtPwd.sendKeys(passWord);
            btnLogin.click();
        } catch (Exception e) {
            LOG.debug("Exception on open GOOGLE: {}", LogUtils.getFullStack(e));
        }
    }

    public boolean isLinkCustomersAppear() {
        return lnkCustomers.isDisplayed();
    }

    public boolean isLoginFailMessageAppear() {
        return iLoginFailMessage.isDisplayed();
    }
}
