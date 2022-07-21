package com.david.bdd.steps.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.david.awb.data.ConfigReader;
import com.david.awb.restapi.Order;
import com.david.bdd.steps.api.dto.*;
import com.david.test.core.BaseAPITest;
import com.david.test.core.dto.ServerInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AWBAPIStepDef extends BaseAPITest {
    protected static final Logger LOG = LoggerFactory.getLogger(AWBAPIStepDef.class);
    ServerInfo serverInfo = ConfigReader.getServerInfo();
    Order order;

    @Given("Login to get token")
    public void loginToGetToken() {
        order = new Order(getSpecification(serverInfo));
    }

    JsonObject createOrderResult;

    @When("Create a new order")
    public void createANewOrder() {
        OrderRequest orderRequest =
                OrderRequest.builder()
                        .service_type("Parcel")
                        .service_level("Standard")
                        .from(
                                OrderRequestTarget.builder()
                                        .name("Ninja Sender Z64441290")
                                        .phone_number("082188889999")
                                        .email("ninja@test.co")
                                        .address(
                                                OrderRequestAddress.builder()
                                                        .address1("903 Toa Payoh North")
                                                        .build())
                                        .build())
                        .to(
                                OrderRequestTarget.builder()
                                        .name("Recipient X")
                                        .phone_number("082188889999")
                                        .email("mickyself.co")
                                        .address(
                                                OrderRequestAddress.builder()
                                                        .address1("999 Toa Payoh North")
                                                        .postcode("318993")
                                                        .build())
                                        .build())
                        .parcel_job(
                                OrderRequestPJ.builder()
                                        .pickup_date("2022-02-09")
                                        .pickup_address(
                                                OrderRequestTarget.builder()
                                                        .name("reservation-2")
                                                        .phone_number("082188881592")
                                                        .email("reservation-1@ninjavan.co")
                                                        .address(
                                                                OrderRequestAddress.builder()
                                                                        .address1(
                                                                                "903 Toa Payoh North")
                                                                        .build())
                                                        .build())
                                        .pickup_timeslot(
                                                OrderRequestTimeSlot.builder()
                                                        .start_time("12:00")
                                                        .end_time("15:00")
                                                        .build())
                                        .delivery_timeslot(
                                                OrderRequestTimeSlot.builder()
                                                        .start_time("09:00")
                                                        .end_time("22:00")
                                                        .build())
                                        .build())
                        .build();
        JsonObject inputData = new Gson().toJsonTree(orderRequest).getAsJsonObject();
        createOrderResult = order.create(inputData);
    }

    @Then("Order is created successful")
    public void orderIsCreatedSuccessful() {
        // Verify Status OK
        Assert.assertEquals("OK", createOrderResult.get("status").getAsString());
    }
}
