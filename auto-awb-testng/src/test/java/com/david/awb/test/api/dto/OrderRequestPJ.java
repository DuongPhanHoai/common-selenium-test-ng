package com.david.awb.test.api.dto;

import lombok.Builder;

@Builder
public class OrderRequestPJ {
    String pickup_date;
    OrderRequestTarget pickup_address;
    OrderRequestTimeSlot pickup_timeslot;
    OrderRequestTimeSlot delivery_timeslot;
}
