package com.david.pfg.test.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.david.pfg.page.LoginPage;
import com.david.test.core.BaseWebTest;

/** Verify fist sample web test sample: - Login script */
public class LoginTests extends BaseWebTest {

    static final String LOGIN_EMAIL = "admin@example.com";
    static final String LOGIN_PWD = "password";

    @Test
    public void loginSuccessful() {

        LoginPage loginPage = new LoginPage(getWebDriverManager());

        // Login
        loginPage.login(LOGIN_EMAIL, LOGIN_PWD);

        // Check If login success
        Assert.assertEquals(
                true, loginPage.isLinkCustomersAppear(), "Verify logging-in successful");
    }
}
