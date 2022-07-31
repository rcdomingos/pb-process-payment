package com.compass.pb.projects.processpayment.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    @JsonProperty("access_token")
    private String token;

    @JsonProperty("token_type")
    private String type;

    @JsonProperty("expires_in")
    private Long expireIn;
}
