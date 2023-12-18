package com.david.aw.test;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeSuite;

import com.david.aw.data.ConfigReader;
import com.david.aw.data.ServerInfo;
import com.david.test.core.BaseWebTest;

public class BaseAWTest extends BaseWebTest {
    protected ServerInfo serverInfo;
    static String env = "production";

    @BeforeSuite(alwaysRun = true)
    protected void beforeSuite() {
        // Get env from ENV variable
        if (StringUtils.isNotBlank(System.getProperty("env"))) env = System.getProperty("env");
        // Read config
        serverInfo = ConfigReader.getServerInfo(env);
    }
}
