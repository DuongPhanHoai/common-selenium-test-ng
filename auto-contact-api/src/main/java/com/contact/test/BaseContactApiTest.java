package com.contact.test;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeSuite;

import com.contact.test.data.ApiConfInfo;
import com.contact.test.data.ConfigReader;
import com.david.test.core.BaseAPITest;

import io.restassured.specification.RequestSpecification;

public class BaseContactApiTest extends BaseAPITest {
    protected ApiConfInfo apiConfInfo;
    static String env = "dev";

    /** Load the configuration before start suite */
    @BeforeSuite(alwaysRun = true)
    protected void beforeSuite() {
        // Get env from ENV variable
        if (StringUtils.isNotBlank(System.getProperty("env"))) env = System.getProperty("env");
        // Read config
        apiConfInfo = ConfigReader.getServerInfo(env);
    }

    protected RequestSpecification getSpecification() throws Exception {
        return getSpecification(
                apiConfInfo.getEmail(),
                apiConfInfo.getPwd(),
                apiConfInfo.getBaseURL(),
                "users/login");
    }
}
