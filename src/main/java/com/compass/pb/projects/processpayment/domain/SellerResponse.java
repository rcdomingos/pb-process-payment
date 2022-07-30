package com.compass.pb.projects.processpayment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class SellerResponse {

    @JsonProperty("seller_id")
    private UUID id;

    @JsonProperty("seller_name")
    private String name;

    @JsonProperty("seller_client_id")
    private String clientId;

    @JsonProperty("seller_api_key")
    private UUID apiKey;
}
