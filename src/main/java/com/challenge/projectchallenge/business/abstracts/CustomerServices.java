package com.challenge.projectchallenge.business.abstracts;

import com.challenge.projectchallenge.dto.CustomerDto;
import com.challenge.projectchallenge.entities.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomerServices extends UserDetailsService {
    Customer register(CustomerDto customer);

    Customer login(String username, String password);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
