package com.challenge.projectchallenge.controllers;

import com.challenge.projectchallenge.business.abstracts.CustomerServices;
import com.challenge.projectchallenge.config.JwtGeneratorValidator;
import com.challenge.projectchallenge.dto.CustomerDto;
import com.challenge.projectchallenge.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final CustomerServices customerServices;


    final AuthenticationManager authManager;


    final JwtGeneratorValidator jwtGenVal;

    @PostMapping("/register")
    public Customer register(@RequestBody CustomerDto customer) {
        return customerServices.register(customer);
    }

    @PostMapping("/login")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public Customer login(@RequestBody CustomerDto customerDto) {
        return customerServices.login(customerDto.getName(), customerDto.getPassword());
    }

    @GetMapping("/genToken")
    public String generateJwtToken(@RequestBody Customer customer) throws Exception {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(customer.getName(), customer.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtGenVal.generateToken(authentication);
    }
}
