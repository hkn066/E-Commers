package com.challenge.projectchallenge.dataAccess;

import com.challenge.projectchallenge.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order>findAllByCustomerId(Long customerId);
}
