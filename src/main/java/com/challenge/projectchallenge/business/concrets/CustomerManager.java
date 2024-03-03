package com.challenge.projectchallenge.business.concrets;

import com.challenge.projectchallenge.business.abstracts.CustomerServices;
import com.challenge.projectchallenge.config.PassEncoder;
import com.challenge.projectchallenge.dataAccess.CartRepository;
import com.challenge.projectchallenge.dataAccess.CustomerRepository;
import com.challenge.projectchallenge.dataAccess.RoleRepository;
import com.challenge.projectchallenge.dto.CustomerDto;
import com.challenge.projectchallenge.entities.Customer;
import com.challenge.projectchallenge.entities.Role;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerServices {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    //    private BCryptPasswordEncoder passwordEncoder ;
    private final PassEncoder passEncoder;


    @Override
    public Customer register(CustomerDto customerDto) {
        Role role = new Role();
        if (customerDto.getRole().equals("USER"))
            role = roleRepository.findByRole("ROLE_USER");
        else if (customerDto.getRole().equals("ADMIN"))
            role = roleRepository.findByRole("ROLE_ADMIN");

        if (role == null) {
            throw new IllegalArgumentException("Invalid role: " + customerDto.getRole());
        }

        Customer user = new Customer();
        if (customerRepository.existsByName(customerDto.getName())){
            user = customerRepository.getByName(customerDto.getName());
            user.setRoles(role);
        } else {
            user.setName(customerDto.getName());
            user.setPassword(passEncoder.encodePass(customerDto.getPassword()));
            user.setRoles(role);
        }
        return customerRepository.save(user);
    }

    @Override
    public Customer login(String username, String password) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByName(username);
        return new org.springframework.security.core.userdetails.User(customer.getName(), customer.getPassword(), mapRolesToAuthorities(customer.getRoles()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @PostConstruct
    private void saveRoles() {
        List<String> roleList = new ArrayList<>();
        roleList.add("ROLE_ADMIN");
        roleList.add("ROLE_USER");
        for (String eachRole : roleList) {
            Role role = new Role(eachRole);
            if (!roleRepository.existsByRole(eachRole)) {
                roleRepository.save(role);
            }
        }
    }

}
