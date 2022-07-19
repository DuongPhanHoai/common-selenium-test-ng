package com.david.test.core;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import com.david.test.core.dto.ServerInfo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * The sample of BaseAPITest - login before running script - store specification for the inheritance
 */
public abstract class BaseAPITest {

    /** Store auth_token after logging-in to the system */
    static String connectSID;

    /** Store the RequestSpecification for all inheritance */
    static RequestSpecification specification;

    public static RequestSpecification getSpecification(ServerInfo serverInfo) {

        if (Objects.isNull(specification)) {
            if (StringUtils.isEmpty(connectSID)) {
                JSONObject loginData = new JSONObject();

                loginData.put("email", serverInfo.getLoginEmail());
                loginData.put("password", serverInfo.getLoginPwd());

                // Get the connectSID
                Response response =
                        RestAssured.given()
                                .baseUri(serverInfo.getBaseURL())
                                .header("Content-Type", "application/json")
                                .body(loginData.toString())
                                .post(serverInfo.getLoginEndPoint())
                                .then()
                                .log()
                                .ifError()
                                .statusCode(200)
                                .body("$", Matchers.hasKey("roles"))
                                . // roles is present in the response
                                body(
                                        "any { it.key == 'roles' }",
                                        Matchers.is(Matchers.notNullValue()))
                                . // authorization_token value is not null - has a value
                                extract()
                                .response();
                response.cookie("connect.sid");
                connectSID = response.cookie("connect.sid");
            }

            // Create the specification with the connectSID
            specification =
                    RestAssured.given()
                            .baseUri(serverInfo.getBaseURL())
                            .header("content-type", "application/json")
                            .cookie("connect.sid", connectSID);
        }
        return specification;
    }

    /** Run before all test to */
    @BeforeMethod(alwaysRun = true)
    protected void beforeMethod() {}
}
