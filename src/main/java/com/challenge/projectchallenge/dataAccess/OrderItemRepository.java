package com.challenge.projectchallenge.dataAccess;

import com.challenge.projectchallenge.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
