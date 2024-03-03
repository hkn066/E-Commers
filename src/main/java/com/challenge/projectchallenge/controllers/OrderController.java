package com.challenge.projectchallenge.controllers;

import com.challenge.projectchallenge.business.abstracts.OrderServices;
import com.challenge.projectchallenge.entities.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderServices orderServices;

    @PostMapping("/{customerId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long customerId){
        return new ResponseEntity<>(orderServices.placeOrder(customerId), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getByOrderId(@PathVariable Long orderId){
        return new ResponseEntity<>(orderServices.getOrder(orderId),HttpStatus.OK);
    }
    @GetMapping("/allOrders/{customerId}")
    public ResponseEntity<List<Order>> getAllOrderForCustomer(@PathVariable Long customerId){
        return new ResponseEntity<>(orderServices.getAllOrdersForCustomer(customerId),HttpStatus.OK);
    }

}



