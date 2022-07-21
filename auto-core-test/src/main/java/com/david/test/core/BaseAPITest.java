package com.david.test.core;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;

import com.david.test.core.dto.ServerInfo;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * The sample of BaseAPITest - login before running script - store specification for the inheritance
 */
public abstract class BaseAPITest {

    /** Store auth_token after logging-in to the system */
    static String accessToken;

    /** Store the RequestSpecification for all inheritance */
    static RequestSpecification specification;

    public static RequestSpecification getSpecification(ServerInfo serverInfo) {

        if (Objects.isNull(specification)) {
            if (StringUtils.isEmpty(accessToken)) {
                JsonObject loginData = new JsonObject();

                loginData.addProperty("client_id", serverInfo.getClientId());
                loginData.addProperty("client_secret", serverInfo.getClientSecret());

                String jsonContent = loginData.toString();

                // Get the connectSID
                Response authResponse =
                        RestAssured.given()
                                .baseUri(serverInfo.getApiBaseURL())
                                .header("Content-Type", "application/json")
                                .body(loginData.toString())
                                .post(serverInfo.getLoginEndPoint())
                                .then()
                                .log()
                                .ifError()
                                .statusCode(200)
                                .body("$", Matchers.hasKey("access_token"))
                                . // roles is present in the response
                                body(
                                        "any { it.key == 'access_token' }",
                                        Matchers.is(Matchers.notNullValue()))
                                . // authorization_token value is not null - has a value
                                extract()
                                .response();

                JsonObject authData =
                        JsonParser.parseString(authResponse.body().asString()).getAsJsonObject();
                accessToken = authData.get("access_token").getAsString();
            }

            // Create the specification with the connectSID
            specification =
                    RestAssured.given()
                            .baseUri(serverInfo.getBaseURL())
                            .header("content-type", "application/json")
                            .header("'authorization", "Bearer " + accessToken);
        }
        return specification;
    }

    /** Run before all test to */
    @BeforeMethod(alwaysRun = true)
    protected void beforeMethod() {}
}
