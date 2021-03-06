package com.david.test.core.base;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/** Element for API test end point */
public class API {
    protected static final Logger LOG = LoggerFactory.getLogger(API.class);
    protected RequestSpecification specification;
    protected String endPoint;

    public API(RequestSpecification specification, String endPoint) {
        this.specification = specification;
        this.endPoint = endPoint;
    }

    /**
     * Wrap for post method easy use
     *
     * @param bodyData input JSONObject
     * @return Response
     */
    protected Response post(Object bodyData) {
        LOG.info("Post to {} with data {}", endPoint, bodyData.toString());
        return given().spec(specification)
                .body(bodyData)
                .when()
                .post(endPoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
