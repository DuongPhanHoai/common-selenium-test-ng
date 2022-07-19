package com.david.pfg.test.api;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.david.pfg.restapi.User;
import com.david.test.core.BaseAPITest;

import io.restassured.response.Response;

/**
 * Verify fist sample API test sample: - Extend the BaseAPITest - Apply the API Endpoint Question
 */
public class UserTests extends BaseAPITest {

    @Test
    public void getUserList() {
        User user = new User(getSpecification());

        // ask a Question
        Response answerRes = user.list();

        // verify Answer
        Assert.assertEquals(200, answerRes.statusCode());

        // get the list Content
        JSONArray list = new JSONArray(answerRes.body().asString());

        Assert.assertEquals(true, list.length() > 0);
    }
}
