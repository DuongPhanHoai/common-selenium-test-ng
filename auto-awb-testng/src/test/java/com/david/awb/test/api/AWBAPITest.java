package com.david.awb.test.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.david.awb.restapi.Order;
import com.david.awb.test.api.dto.*;
import com.david.awb.test.data.ConfigReader;
import com.david.test.core.BaseAPITest;
import com.david.test.core.dto.ServerInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Verify fist sample API test sample: - Extend the BaseAPITest - Apply the API Endpoint Question
 */
public class AWBAPITest extends BaseAPITest {

    ServerInfo serverInfo = ConfigReader.getServerInfo();

    @Test
    public void createNewOrder() {
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
        String testString = inputData.toString();
        // Create new Order
        Order order = new Order(getSpecification(serverInfo));
        JsonObject res = order.create(inputData);

        // Verify Status OK
        Assert.assertEquals("OK", res.get("status").getAsString());
    }
}
