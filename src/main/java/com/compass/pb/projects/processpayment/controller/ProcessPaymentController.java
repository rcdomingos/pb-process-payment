package com.compass.pb.projects.processpayment.controller;

import com.compass.pb.projects.processpayment.domain.PaymentRequest;
import com.compass.pb.projects.processpayment.domain.PaymentResponse;
import com.compass.pb.projects.processpayment.service.ProcessPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/payments")
public class ProcessPaymentController {

    private final ProcessPaymentService service;

    @PostMapping("/credit-card")
    public ResponseEntity<PaymentResponse> processNewPayment(@RequestBody @Valid PaymentRequest payment) {
        return ResponseEntity.ok(service.processPayment(payment));
    }
}
