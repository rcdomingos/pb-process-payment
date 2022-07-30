package com.compass.pb.projects.processpayment.dto;

import com.compass.pb.projects.processpayment.constants.CustomerIdType;
import com.compass.pb.projects.processpayment.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class CustomerIdentification {

    @NotEmpty
    @JsonProperty("document_number")
    private String number;

    @NotBlank
    @ValueOfEnum(enumClass = CustomerIdType.class, message = "valor inválido,aceito apenas: CPF,CNPJ,RG,CNH")
    @JsonProperty("document_type")
    private String type;
}
