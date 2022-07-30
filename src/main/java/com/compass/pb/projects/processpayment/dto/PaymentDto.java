package com.compass.pb.projects.processpayment.dto;

import com.compass.pb.projects.processpayment.constants.CurrencyType;
import com.compass.pb.projects.processpayment.constants.CustomerIdType;
import com.compass.pb.projects.processpayment.constants.PaymentStatus;
import com.compass.pb.projects.processpayment.constants.PaymentType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentDto {
    private UUID id;

    private UUID sellerId;

    private String customerNumber;

    private CustomerIdType customerIdType;

    private PaymentType paymentTypeId;

    private String paymentMethodNumber;

    private CurrencyType currencyType;

    private Double transactionAmount;

    private PaymentStatus status;

    private Long authorizationCode;

    private int reasonCode;

    private String reasonMessage;

    private LocalDateTime authorizedAt;

    private LocalDateTime receivedAt;
}
