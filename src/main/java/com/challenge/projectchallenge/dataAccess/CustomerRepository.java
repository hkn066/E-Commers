package com.challenge.projectchallenge.dataAccess;

import com.challenge.projectchallenge.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByName(String username);

    boolean existsByName(String name);

    Customer getByName(String name);
}

