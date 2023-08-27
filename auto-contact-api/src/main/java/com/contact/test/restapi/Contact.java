package com.contact.test.restapi;

import org.testng.Assert;

import com.contact.test.restapi.dto.CreateContactRequest;
import com.david.test.core.base.API;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Contact extends API {
    static final String END_POINT = "contacts";

    public Contact(RequestSpecification specification) {
        super(specification, END_POINT);
    }

    public JsonObject list() {
        Response answerRes = get();
        Assert.assertEquals(200, answerRes.statusCode());
        return JsonParser.parseString(answerRes.body().asString()).getAsJsonObject();
    }

    public JsonObject create(CreateContactRequest request) {
        Response answerRes = post(request.toString());
        Assert.assertEquals(200, answerRes.statusCode());
        return JsonParser.parseString(answerRes.body().asString()).getAsJsonObject();
    }
}
