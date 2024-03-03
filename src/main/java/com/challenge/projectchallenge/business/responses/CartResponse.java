package com.challenge.projectchallenge.business.responses;

import com.challenge.projectchallenge.entities.CartItem;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private String productName;
    private Long productId;
    private int quantity;
    private Double totalPrice;

}
