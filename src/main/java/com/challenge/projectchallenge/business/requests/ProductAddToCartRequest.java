package com.challenge.projectchallenge.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddToCartRequest {
    private Long customerId;
    private Long productId;
    private int quantity;

}
