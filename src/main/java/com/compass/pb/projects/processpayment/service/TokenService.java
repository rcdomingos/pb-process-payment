package com.compass.pb.projects.processpayment.service;

import com.compass.pb.projects.processpayment.entity.SellerEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class TokenService {

    @Value("${app-config.jwt.expiration}")
    private String expiration;

    @Value("${app-config.jwt.secret}")
    private String secret;

    public String tokenGenerate(Authentication authentication) {
        SellerEntity sellerLogado = (SellerEntity) authentication.getPrincipal();
        Date actualDate = new Date();
        Date expirationDate = new Date(actualDate.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Getway pagBank PB")
                .setSubject(sellerLogado.getSellerId().toString())
                .setIssuedAt(actualDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.debug("isValidToken() - Error - Error to validate: {}", e.getMessage());
            return false;
        }
    }

    public UUID getSellerId(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return UUID.fromString(body.getSubject());
    }


}
