package com.david.awb.test.api.dto;

import lombok.Builder;

@Builder
public class OrderRequestAddress {
    String address1;
    @Builder.Default String address2 = "";
    @Builder.Default String country = "SG";
    @Builder.Default String postcode = "511200";
}
