package com.david.pfg.test.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.david.pfg.page.LoginPage;
import com.david.pfg.test.data.ConfigReader;
import com.david.test.core.BaseWebTest;
import com.david.test.core.dto.ServerInfo;

/** Verify fist sample web test sample: - Login script */
public class LoginTests extends BaseWebTest {
    ServerInfo serverInfo = ConfigReader.getServerInfo();

    @Test
    public void loginSuccessful() {

        LoginPage loginPage = new LoginPage(getWebDriverManager());

        // Login
        loginPage.login(serverInfo);

        // Check If login success
        Assert.assertEquals(
                true, loginPage.isLinkCustomersAppear(), "Verify logging-in successful");
    }

    @Test
    public void loginFailure() {

        LoginPage loginPage = new LoginPage(getWebDriverManager());

        // Login
        loginPage.login(
                serverInfo.getLoginEmail(),
                serverInfo.getLoginPwd() + "1",
                serverInfo.getBaseURL());

        // Check If login success
        Assert.assertEquals(
                true, loginPage.isLoginFailMessageAppear(), "Verify logging-in failure");
    }
}
