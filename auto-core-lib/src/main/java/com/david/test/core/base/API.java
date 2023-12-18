package com.david.test.core.base;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

/** Element for API test end point */
@Slf4j
public class API {
    protected RequestSpecification specification;
    protected String endPoint;

    public API(RequestSpecification specification, String endPoint) {
        this.specification = specification;
        this.endPoint = endPoint;
    }

    /**
     * Wrap for post method easy use
     *
     * @param bodyData input JsonObject
     * @return Response
     */
    protected Response post(Object bodyData) {
        Response res =
                given().spec(specification)
                        .body(bodyData)
                        .when()
                        .post(endPoint)
                        .then()
                        .extract()
                        .response();
        log.info(
                "Post to {} with data {}: get response: {}",
                endPoint,
                bodyData.toString(),
                res.asString());
        return res;
    }

    protected Response post(Object bodyData, String additionalEndPoint) {
        String newEndPoint = endPoint + additionalEndPoint;
        Response res =
                given().spec(specification)
                        .body(bodyData)
                        .when()
                        .post(newEndPoint)
                        .then()
                        .extract()
                        .response();
        log.info(
                "Post to {} with data {}: get response: {}",
                newEndPoint,
                bodyData.toString(),
                res.asString());
        return res;
    }

    protected Response put(Object bodyData, String additionalEndPoint) {
        String newEndPoint = endPoint + additionalEndPoint;
        Response res =
                given().spec(specification)
                        .body(bodyData)
                        .when()
                        .put(newEndPoint)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        log.info(
                "Put to {} with data {}: get response: {}",
                newEndPoint,
                bodyData.toString(),
                res.asString());
        return res;
    }

    protected Response get() {
        log.info("Get to {} with data {}", endPoint);
        Response res =
                given().spec(specification)
                        .when()
                        .get(endPoint)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        log.info("Put to {}: get response: {}", endPoint, res.asString());
        return res;
    }
}
