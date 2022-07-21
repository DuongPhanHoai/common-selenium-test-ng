package com.david.bdd.steps.api.dto;

import lombok.Builder;

@Builder
public class OrderRequestTimeSlot {
    String start_time;
    String end_time;
    @Builder.Default String timezone = "Asia/Singapore";
}
