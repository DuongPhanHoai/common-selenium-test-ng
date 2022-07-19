package com.david.pfg.test.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.david.pfg.restapi.Question;
import com.david.test.core.BaseAPITest;

import io.restassured.response.Response;

/**
 * Verify fist sample API test sample: - Extend the BaseAPITest - Apply the API Endpoint Question
 */
public class SampleApiTests extends BaseAPITest {
    Question question = new Question(getSpecification());

    @Test
    public void askQuestion1() {

        // ask a Question
        Response answerRes = question.ask("Diamond");

        // verify Answer
        Assert.assertEquals(200, answerRes.statusCode());
    }
}
