package com.challenge.projectchallenge.business.abstracts;

import com.challenge.projectchallenge.business.requests.ProductAddToCartRequest;
import com.challenge.projectchallenge.business.requests.ProductRemoveToCartRequest;
import com.challenge.projectchallenge.business.requests.UpdateProductToCart;
import com.challenge.projectchallenge.business.responses.CartResponse;
import com.challenge.projectchallenge.entities.Cart;

import java.util.List;

public interface CartServices {
    Cart getCart(Long customerId);
    Cart emptyCart(Long cartId);

    void updateProductToCart(UpdateProductToCart updateProductToCart);

    List<CartResponse> addProductToCart(ProductAddToCartRequest productAddToCartRequest);
    void removeProductFromCart(ProductRemoveToCartRequest productAddToCartRequest);
}
