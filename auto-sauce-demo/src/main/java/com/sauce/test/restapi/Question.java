package com.sauce.test.restapi;

import org.json.JSONObject;

import com.david.test.core.base.API;

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
        JSONObject data = new JSONObject();
        data.put("question", question);
        return post(data);
    }
}
