package com.david.awb.restapi;

import com.david.test.core.base.API;
import com.google.gson.JsonObject;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/** Very first coding sample for API END point */
public class Question extends API {
    public Question(RequestSpecification specification) {
        super(specification, "/v1/create");
    }

    /**
     * Ask by API
     *
     * @param question the input question
     * @return the Response
     */
    public Response ask(String question) {
        JsonObject data = new JsonObject();
        data.addProperty("question", question);
        return post(data);
    }
}
