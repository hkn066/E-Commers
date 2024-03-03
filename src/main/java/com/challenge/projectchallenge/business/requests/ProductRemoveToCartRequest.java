package com.challenge.projectchallenge.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRemoveToCartRequest {
    private Long productId;
    private Long customerId;

}
