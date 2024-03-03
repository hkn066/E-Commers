package com.challenge.projectchallenge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "orderItems")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity {
    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "totalPrice")
    private Double totalPrice;
}
