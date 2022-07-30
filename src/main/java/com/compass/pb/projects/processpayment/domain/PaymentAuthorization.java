package com.compass.pb.projects.processpayment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAuthorization {
    @JsonProperty("authorization_code")
    private Long authorizationCode;

    @JsonProperty("authorized_at")
    private LocalDateTime authorizedAt;

    @JsonProperty("reason_code")
    private int reasonCode;

    @JsonProperty("reason_message")
    private String reasonMessage;

}
