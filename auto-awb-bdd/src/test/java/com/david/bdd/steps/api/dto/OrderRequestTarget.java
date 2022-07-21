package com.david.bdd.steps.api.dto;

import lombok.Builder;

@Builder
public class OrderRequestTarget {
    String name;
    String phone_number;
    String email;
    OrderRequestAddress address;
}
