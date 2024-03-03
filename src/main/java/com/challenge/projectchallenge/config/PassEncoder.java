package com.challenge.projectchallenge.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PassEncoder {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encodePass(String password){
        return passwordEncoder.encode(password);
    }
}
