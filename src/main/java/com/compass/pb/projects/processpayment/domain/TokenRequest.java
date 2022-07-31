package com.compass.pb.projects.processpayment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class TokenRequest {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("api_key")
    private String apiKey;

}
