package com.david.awb.restapi;

import org.testng.Assert;

import com.david.test.core.base.API;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/** Very first coding sample for API END point */
public class Order extends API {
    static final String END_POINT = "sg/order-create/orders";

    public Order(RequestSpecification specification) {
        super(specification, END_POINT);
    }

    /**
     * Ask by API
     *
     * @param payload the input question
     * @return the Response
     */
    public JsonObject create(JsonObject payload) {
        Response answerRes = post(payload.toString());
        Assert.assertEquals(200, answerRes.statusCode());
        return JsonParser.parseString(answerRes.body().asString()).getAsJsonObject();
    }
}
