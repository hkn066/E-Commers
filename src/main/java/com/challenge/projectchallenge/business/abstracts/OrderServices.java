package com.challenge.projectchallenge.business.abstracts;

import com.challenge.projectchallenge.entities.Order;

import java.util.List;

public interface OrderServices {
    Order placeOrder(Long customerId);
    Order getOrder(Long orderId);
    List<Order> getAllOrdersForCustomer(Long customerId);
}
