package com.compass.pb.projects.processpayment.controller;

import com.compass.pb.projects.processpayment.domain.TokenRequest;
import com.compass.pb.projects.processpayment.domain.response.TokenResponse;
import com.compass.pb.projects.processpayment.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager manager;

    private final TokenService tokenService;

    @Value("${app-config.jwt.expiration}")
    private String expiration;

    @PostMapping
    public ResponseEntity<TokenResponse> authentication(@RequestBody TokenRequest request) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(request.getClientId(), request.getApiKey());
        try {
            Authentication authenticate = manager.authenticate(login);
            String token = tokenService.tokenGenerate(authenticate);

            return ResponseEntity.ok(generateTokenResponse(token));
        } catch (AuthenticationException e) {
            log.error("authentication() - Error to authentication: {} ", e.getMessage());
            throw e;
        }
    }

    private TokenResponse generateTokenResponse(String token) {
        long expireIn = Long.parseLong(expiration) / 1000; //valor em segundos
        return new TokenResponse(token, "Bearer", expireIn);
    }
}
