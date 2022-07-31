package com.compass.pb.projects.processpayment.domain.response;

import com.compass.pb.projects.processpayment.constants.CurrencyType;
import com.compass.pb.projects.processpayment.constants.PaymentStatus;
import com.compass.pb.projects.processpayment.domain.PaymentAuthorization;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentResponse {

    @JsonProperty("payment_id")
    private UUID paymentId;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("transaction_amount")
    private Double transactionAmount;

    @JsonProperty("currency")
    private CurrencyType currencyType;

    @JsonProperty("status")
    private PaymentStatus status;

    @JsonProperty("received_at")
    private LocalDateTime receivedAt;

    @JsonProperty("authorization")
    private PaymentAuthorization authorization;

}
