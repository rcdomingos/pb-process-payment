package com.compass.pb.projects.processpayment.domain;

import com.compass.pb.projects.processpayment.constants.CardBrand;
import com.compass.pb.projects.processpayment.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CardRequest {

    @NotEmpty
    @JsonProperty("number_token")
    private String number;

    @NotEmpty
    @JsonProperty("cardholder_name")
    private String cardholderName;

    @NotEmpty
    @Pattern(regexp = "^\\d{3}$", message = "deve ser apenas numerico de 3 digitos")
    @JsonProperty("security_code")
    private String securityCode;

    @NotBlank
    @ValueOfEnum(enumClass = CardBrand.class, message = "valor inválido,aceito apenas: VISA,MASTERCARD,ELO,AMERICAN_EXPRESS")
    @JsonProperty("brand")
    private String brand;

    @NotBlank
    @Pattern(regexp = "^[1-9]$|^1[0-2]$", message = "deve ser 1 a 12")
    @JsonProperty("expiration_month")
    private String expirationMonth;

    @NotBlank
    @Size(min = 2, max = 4, message = "deve ter 2 ou 4 digitos")
    @JsonProperty("expiration_year")
    private String expirationYear;
}
