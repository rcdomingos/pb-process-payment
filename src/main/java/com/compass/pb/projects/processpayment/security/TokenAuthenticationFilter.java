package com.compass.pb.projects.processpayment.security;

import com.compass.pb.projects.processpayment.entity.SellerEntity;
import com.compass.pb.projects.processpayment.repository.SellerRepository;
import com.compass.pb.projects.processpayment.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private SellerRepository sellerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        if (tokenService.isValidToken(token)) clientAuthentication(token);

        filterChain.doFilter(request, response);
    }

    private void clientAuthentication(String token) {
        log.debug("clientAuthentication() - start - authentication token {} ", token);
        UUID sellerId = tokenService.getSellerId(token);
        SellerEntity seller = sellerRepository.findById(sellerId).orElseThrow();
        UsernamePasswordAuthenticationToken authenticated = new UsernamePasswordAuthenticationToken(seller, null, seller.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        log.debug("clientAuthentication() - end");
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        log.debug("getToken() - header {} ", token);
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        } else {
            return token.substring(7, token.length());
        }
    }
}
