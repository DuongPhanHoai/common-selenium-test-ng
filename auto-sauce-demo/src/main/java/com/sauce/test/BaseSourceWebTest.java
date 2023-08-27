package com.sauce.test;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeSuite;

import com.david.test.core.BaseWebTest;
import com.sauce.test.data.ConfigReader;
import com.sauce.test.data.ServerInfo;

public class BaseSourceWebTest extends BaseWebTest {
    protected ServerInfo serverInfo;
    static String env = "dev";

    @BeforeSuite(alwaysRun = true)
    protected void beforeSuite() {
        // Get env from ENV variable
        if (StringUtils.isNotBlank(System.getProperty("env"))) env = System.getProperty("env");
        // Read config
        serverInfo = ConfigReader.getServerInfo(env);
    }
}
