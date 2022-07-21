package com.david.awb.test.api.dto;

import lombok.Builder;

@Builder
public class OrderRequest {
    String service_type;
    String service_level;
    OrderRequestTarget from;
    OrderRequestTarget to;
    OrderRequestPJ parcel_job;
}
