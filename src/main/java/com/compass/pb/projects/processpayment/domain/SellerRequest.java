package com.compass.pb.projects.processpayment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SellerRequest {

    @JsonProperty("seller_name")
    private String name;

    @JsonProperty("seller_client_id")
    private String clientId;
}
