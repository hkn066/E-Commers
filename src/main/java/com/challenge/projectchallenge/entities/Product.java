package com.challenge.projectchallenge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product  extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "stockQuantity")
    private int stockQuantity;
}
