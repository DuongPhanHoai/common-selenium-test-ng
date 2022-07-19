package com.david.pfg.restapi;

import com.david.test.core.base.API;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/** Very first coding sample for API END point */
public class User extends API {

    static final String END_POINT = "api/users";

    public User(RequestSpecification specification) {
        super(specification, END_POINT);
    }

    /**
     * Ask by API
     *
     * @return the Response
     */
    public Response list() {
        return get();
    }
}
