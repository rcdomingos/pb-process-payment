package com.compass.pb.projects.processpayment.constants;

import lombok.Getter;

@Getter
public enum ReasonCodes {
    OK(0, "transaction approved"),
    INVALID_CARD(40, "informed card is invalid"),
    EXPIRED(41, "expired credit card"),
    NO_FUNDS(45, "insufficient funds");

    private final int code;
    private final String message;

    ReasonCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
