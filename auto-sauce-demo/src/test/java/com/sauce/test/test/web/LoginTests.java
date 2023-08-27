package com.sauce.test.test.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sauce.test.BaseSourceWebTest;
import com.sauce.test.page.HomePage;
import com.sauce.test.page.LoginPage;

/** Verify fist sample web test sample: - Extend the BaseSourceWebTest - Using the POM */
public class LoginTests extends BaseSourceWebTest {

    /** Steps: - Open page, login with valid user >> Verify: - Home page open successful */
    @Test
    public void loginSuccessful() {
        LoginPage loginPage = new LoginPage(getWebDriverManager());
        HomePage homePage = new HomePage(getWebDriverManager());
        // Open page, login with valid user
        loginPage.login(serverInfo);

        // Home page open successful
        Assert.assertTrue(homePage.isInventoryContainerAppear(), "Home page not appear");
    }
}
