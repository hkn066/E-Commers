package com.challenge.projectchallenge.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductToCart {
    private Long productId;
    private Long customerId;
    private int quantity;
}
