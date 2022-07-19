package com.david.pfg.restapi;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.david.test.core.base.API;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Food extends API {

    static final String END_POINT = "api/foods";

    public Food(RequestSpecification specification) {
        super(specification, END_POINT);
    }

    /**
     * Get the cat list
     *
     * @return Category list
     */
    public JSONArray getCategoryList() {
        Response answerRes = get();
        Assert.assertEquals(200, answerRes.statusCode());
        return new JSONArray(answerRes.body().asString());
    }

    /**
     * Create new Item
     *
     * @param itemName
     * @param catId
     * @param quantity
     * @return the id of new Item
     */
    public JSONObject addNewItem(String itemName, String catId, String quantity) {
        JSONObject newItem = new JSONObject();
        newItem.put("name", itemName);
        newItem.put("categoryId", catId);
        newItem.put("quantity", quantity);

        Response answerRes = post(newItem.toString(), String.format("/%s/items", catId));
        Assert.assertEquals(200, answerRes.statusCode());
        return new JSONObject(answerRes.body().asString());
    }

    public JSONObject editItem(String newName, String itemId, String catId, String quantity) {
        JSONObject newItem = new JSONObject();
        newItem.put("name", newName);
        newItem.put("categoryId", catId);
        newItem.put("quantity", quantity);
        newItem.put("_id", itemId);

        Response answerRes = put(newItem.toString(), String.format("/%s/items/%s", catId, itemId));
        Assert.assertEquals(200, answerRes.statusCode());
        return new JSONObject(answerRes.body().asString());
    }
}
