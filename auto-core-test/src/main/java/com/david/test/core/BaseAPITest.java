package com.david.test.core;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
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

    public static RequestSpecification getSpecification(
            String email, String password, String baseURL, String loginEndPoint) throws Exception {

        if (Objects.isNull(specification)) {
            if (StringUtils.isEmpty(accessToken)) {
                JsonObject loginData = new JsonObject();

                loginData.addProperty("email", email);
                loginData.addProperty("password", password);

                String jsonContent = loginData.toString();

                // Get the connectSID
                Response authResponse =
                        RestAssured.given()
                                .baseUri(baseURL)
                                .header("Content-Type", "application/json")
                                .body(loginData.toString())
                                .post(loginEndPoint)
                                .then()
                                .log()
                                .ifError()
                                .statusCode(200)
                                . // authorization_token value is not null - has a value
                                extract()
                                .response();
                Headers headers = authResponse.headers();

                for (Header header : headers) {
                    if (header.getName().equals("Set-Cookie")
                            && header.getValue().startsWith("token")) {
                        String setCookie = header.getValue();
                        accessToken = setCookie.substring(6, setCookie.indexOf(';'));
                        break;
                    }
                }
            }

            if (Objects.nonNull(accessToken))
                // Create the specification with the connectSID
                specification =
                        RestAssured.given()
                                .baseUri(baseURL)
                                .header("content-type", "application/json")
                                .header("authorization", "Bearer " + accessToken);
            else throw new Exception("Login failed, please check");
        }
        return specification;
    }
}
