package com.compass.pb.projects.processpayment.domain.request;

import com.compass.pb.projects.processpayment.constants.CurrencyType;
import com.compass.pb.projects.processpayment.constants.PaymentType;
import com.compass.pb.projects.processpayment.dto.CustomerIdentification;
import com.compass.pb.projects.processpayment.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PaymentRequest {

    @NotEmpty
    @JsonProperty("seller_id")
    private String sellerId;

    @Valid
    @NotNull
    @JsonProperty("customer")
    private CustomerIdentification customer;


    @NotBlank
    @ValueOfEnum(enumClass = PaymentType.class, message = "valor inválido,aceito apenas: CREDIT_CARD")
    @JsonProperty("payment_type")
    private String paymentTypeId;

    @NotBlank
    @ValueOfEnum(enumClass = CurrencyType.class, message = "valor inválido,aceito apenas:BRL")
    @JsonProperty("currency")
    private String currencyType;

    @NotNull
    @JsonProperty("transaction_amount")
    private Double transactionAmount;

    @Valid
    @NotNull
    @JsonProperty("card")
    private CardRequest card;

}
