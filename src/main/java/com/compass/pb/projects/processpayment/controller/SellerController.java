package com.compass.pb.projects.processpayment.controller;

import com.compass.pb.projects.processpayment.domain.SellerRequest;
import com.compass.pb.projects.processpayment.domain.SellerResponse;
import com.compass.pb.projects.processpayment.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/seller")
public class SellerController {

    private final SellerService service;

    @Value("${app-config.apikey}")
    private String internalApikey;

    @PostMapping
    public ResponseEntity<SellerResponse> addNewSeller(@RequestHeader(value = "apikey") String apikey,
                                                       @RequestBody SellerRequest request) {
        if (!internalApikey.equals(apikey)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(service.addNewSeller(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerResponse> getSellerById(@PathVariable UUID id,
                                                        @RequestHeader(value = "apikey") String apikey) {
        if (!internalApikey.equals(apikey)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(service.getSellerById(id));
    }
}
