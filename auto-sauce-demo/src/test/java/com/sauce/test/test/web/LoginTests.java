package com.sauce.test.test.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sauce.test.BaseSourceWebTest;
import com.sauce.test.data.ServerInfo;
import com.sauce.test.page.HomePage;
import com.sauce.test.page.LoginPage;

/** Verify fist sample web test sample: - Extend the BaseSourceWebTest - Using the POM */
public class LoginTests extends BaseSourceWebTest {

    /** Steps: - Open page, login with valid user >> Verify: - Home page open successful */
    @Test
    public void loginSuccessful() {
        // Declare Pages
        LoginPage loginPage = new LoginPage(getWebDriverManager());
        HomePage homePage = new HomePage(getWebDriverManager());

        // Open page, login with valid user
        loginPage.login(serverInfo);

        // Home page open successful
        Assert.assertTrue(homePage.isInventoryContainerAppear(), "Home page not appear");
    }

    /** Steps: - Open page, login with invalid user >> Verify: - Error appear */
    @Test
    public void loginFailed() {
        // Declare Pages
        LoginPage loginPage = new LoginPage(getWebDriverManager());
        HomePage homePage = new HomePage(getWebDriverManager());

        // Open page, login with valid user
        loginPage.login(
                ServerInfo.builder()
                        .baseURL(serverInfo.getBaseURL())
                        .standardUsername(serverInfo.getStandardUsername() + "123456")
                        .standardUserPwd(serverInfo.getStandardUserPwd())
                        .build());

        // Home page open successful
        String loginErr = loginPage.getErrorMsg();
        Assert.assertTrue(
                loginErr.contains("Username and password do not match any"),
                "Verify from text value: " + loginErr);
    }
}
