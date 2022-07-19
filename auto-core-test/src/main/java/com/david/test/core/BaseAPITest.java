package com.david.test.core;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * The sample of BaseAPITest - login before running script - store specification for the inheritance
 */
public abstract class BaseAPITest {

    static final String URI = "http://localhost:8080/";
    static final String LOGIN_END_POINT = "api/auth/signin";
    static final String LOGIN_EMAIL = "admin@example.com";
    static final String LOGIN_PWD = "password";

    /** Store auth_token after logging-in to the system */
    static String connectSID;

    /** Store the RequestSpecification for all inheritance */
    static RequestSpecification specification;

    public static RequestSpecification getSpecification() {
        return specification;
    }

    /** Run before all test to */
    @BeforeMethod(alwaysRun = true)
    protected void beforeMethod() {
        initSpecification();
    }

    /** Init the Specification with Token */
    protected void initSpecification() {

        if (StringUtils.isEmpty(connectSID)) {
            JSONObject loginData = new JSONObject();

            loginData.put("email", LOGIN_EMAIL);
            loginData.put("password", LOGIN_PWD);

            // Get the connectSID
            Response response =
                    RestAssured.given()
                            .baseUri(URI)
                            .header("Content-Type", "application/json")
                            .body(loginData.toString())
                            .post(LOGIN_END_POINT)
                            .then()
                            .log()
                            .ifError()
                            .statusCode(200)
                            .body("$", Matchers.hasKey("roles"))
                            . // roles is present in the response
                            body("any { it.key == 'roles' }", Matchers.is(Matchers.notNullValue()))
                            . // authorization_token value is not null - has a value
                            extract()
                            .response();
            response.cookie("connect.sid");
            connectSID = response.cookie("connect.sid");
        }

        // Create the specification with the connectSID
        specification =
                RestAssured.given()
                        .baseUri(URI)
                        .header("Content-Type", "application/json")
                        .cookie("connect.sid", connectSID);
    }
}
