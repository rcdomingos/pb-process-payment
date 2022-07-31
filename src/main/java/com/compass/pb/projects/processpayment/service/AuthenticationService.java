package com.compass.pb.projects.processpayment.service;

import com.compass.pb.projects.processpayment.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService implements UserDetailsService {

    private final SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return sellerRepository.findByClientId(username).orElseThrow(() -> new UsernameNotFoundException("Invalid informations"));
    }
}
