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

    /** Store auth_token after logging-in to the system */
    static String auth_token;

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

        if (StringUtils.isNotBlank(auth_token)) {
            JSONObject loginData = new JSONObject();

            loginData.put("usr", "ABC");
            loginData.put("key", "GetFromPropertiesFile");

            // Do some request or something to getToken
            Response response =
                    RestAssured.given()
                            .baseUri("http://google.com/api")
                            .header("Content-Type", "application/json")
                            .body(loginData.toString())
                            .post("/login")
                            .then()
                            .log()
                            .ifError()
                            .statusCode(200)
                            .contentType("application/vnd.api+json")
                            .body("$", Matchers.hasKey("authorization_token"))
                            . // authorization_token is present in the response
                            body(
                                    "any { it.key == 'authorization_token' }",
                                    Matchers.is(Matchers.notNullValue()))
                            . // authorization_token value is not null - has a value
                            extract()
                            .response();

            auth_token = response.path("authorization_token").toString();
        }

        specification =
                RestAssured.given()
                        .baseUri("http://google.com/api")
                        .header("Authorization", "Bearer " + auth_token);
    }
}
