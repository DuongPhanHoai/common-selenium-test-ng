package com.david.pfg.test.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.david.pfg.restapi.Food;
import com.david.pfg.test.data.ConfigReader;
import com.david.pfg.test.data.ExcelReader;
import com.david.test.core.BaseAPITest;
import com.david.test.core.dto.ServerInfo;

public class FoodTests extends BaseAPITest {
    ServerInfo serverInfo = ConfigReader.getServerInfo();

    @Test
    public void addEditFoodItemHardCode() {

        final String name = "NewItemTest";

        final String newName = "NewItemTest123";

        Food food = new Food(getSpecification(serverInfo));
        // Get the Category List
        JSONArray catList = food.getCategoryList();

        // Create new Item
        JSONObject newItemResponse =
                food.addNewItem(
                        name, catList.getJSONObject(catList.length() - 1).getString("_id"), "12");

        // Scan to find the new Item in the response list
        JSONObject found = null;
        JSONArray itemList = newItemResponse.getJSONArray("items");
        for (int i = 0; i < itemList.length(); i++) {
            JSONObject item = itemList.getJSONObject(i);
            if (item.getString("name").equals(name)) {
                found = item;
                break;
            }
        }

        Assert.assertNotNull(found, "Verify the found the new Item");

        // edit Item
        JSONObject editItemResponse =
                food.editItem(
                        newName,
                        found.getString("_id"),
                        catList.getJSONObject(catList.length() - 1).getString("_id"),
                        "12");

        // Scan to find the new Item in the response list
        JSONObject foundEditItem = null;
        JSONArray foundItemList = editItemResponse.getJSONArray("items");
        for (int i = 0; i < foundItemList.length(); i++) {
            JSONObject item = foundItemList.getJSONObject(i);
            if (item.getString("name").equals(newName)) {
                foundEditItem = item;
                break;
            }
        }

        Assert.assertNotNull(foundEditItem, "Verify the found the edited Item");
        food.editItem(
                name,
                found.getString("_id"),
                catList.getJSONObject(catList.length() - 1).getString("_id"),
                "12");
    }

    /**
     * Hard Code data source
     *
     * @return the multi array data source
     */
    @DataProvider(name = "EditFoodItemData")
    public static Object[][] editFoodItemData() {
        return new Object[][] {{"name", "newName"}, {"otherName", "otherNewName"}};
    }

    @Test(dataProvider = "EditFoodItemData")
    public void addEditFoodItemFromDataSource(String name, String newName) {

        Food food = new Food(getSpecification(serverInfo));
        // Get the Category List
        JSONArray catList = food.getCategoryList();

        // Create new Item
        JSONObject newItemResponse =
                food.addNewItem(
                        name, catList.getJSONObject(catList.length() - 1).getString("_id"), "12");

        // Scan to find the new Item in the response list
        JSONObject found = null;
        JSONArray itemList = newItemResponse.getJSONArray("items");
        for (int i = 0; i < itemList.length(); i++) {
            JSONObject item = itemList.getJSONObject(i);
            if (item.getString("name").equals(name)) {
                found = item;
                break;
            }
        }

        Assert.assertNotNull(found, "Verify the found the new Item");

        // edit Item
        JSONObject editItemResponse =
                food.editItem(
                        newName,
                        found.getString("_id"),
                        catList.getJSONObject(catList.length() - 1).getString("_id"),
                        "12");

        // Scan to find the new Item in the response list
        JSONObject foundEditItem = null;
        JSONArray foundItemList = editItemResponse.getJSONArray("items");
        for (int i = 0; i < foundItemList.length(); i++) {
            JSONObject item = foundItemList.getJSONObject(i);
            if (item.getString("name").equals(newName)) {
                foundEditItem = item;
                break;
            }
        }

        Assert.assertNotNull(foundEditItem, "Verify the found the edited Item");
        food.editItem(
                name,
                found.getString("_id"),
                catList.getJSONObject(catList.length() - 1).getString("_id"),
                "12");
    }

    @DataProvider(name = "EditFoodItemExcel")
    public Object[][] editFoodItemExcel() {
        Object[][] testObjArray = ExcelReader.readSheetContent("/EditFoodIem.xlsx", "Sheet1");
        return (testObjArray);
    }

    @Test(dataProvider = "EditFoodItemExcel")
    public void addEditFoodItemFromExcel(String name, String newName) {

        Food food = new Food(getSpecification(serverInfo));
        // Get the Category List
        JSONArray catList = food.getCategoryList();

        // Create new Item
        JSONObject newItemResponse =
                food.addNewItem(
                        name, catList.getJSONObject(catList.length() - 1).getString("_id"), "12");

        // Scan to find the new Item in the response list
        JSONObject found = null;
        JSONArray itemList = newItemResponse.getJSONArray("items");
        for (int i = 0; i < itemList.length(); i++) {
            JSONObject item = itemList.getJSONObject(i);
            if (item.getString("name").equals(name)) {
                found = item;
                break;
            }
        }

        Assert.assertNotNull(found, "Verify the found the new Item");

        // edit Item
        JSONObject editItemResponse =
                food.editItem(
                        newName,
                        found.getString("_id"),
                        catList.getJSONObject(catList.length() - 1).getString("_id"),
                        "12");

        // Scan to find the new Item in the response list
        JSONObject foundEditItem = null;
        JSONArray foundItemList = editItemResponse.getJSONArray("items");
        for (int i = 0; i < foundItemList.length(); i++) {
            JSONObject item = foundItemList.getJSONObject(i);
            if (item.getString("name").equals(newName)) {
                foundEditItem = item;
                break;
            }
        }

        Assert.assertNotNull(foundEditItem, "Verify the found the edited Item");
        food.editItem(
                name,
                found.getString("_id"),
                catList.getJSONObject(catList.length() - 1).getString("_id"),
                "12");
    }
}
