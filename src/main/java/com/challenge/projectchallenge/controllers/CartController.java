package com.challenge.projectchallenge.controllers;

import com.challenge.projectchallenge.business.abstracts.CartServices;
import com.challenge.projectchallenge.business.requests.ProductAddToCartRequest;
import com.challenge.projectchallenge.business.requests.ProductRemoveToCartRequest;
import com.challenge.projectchallenge.business.responses.CartResponse;
import com.challenge.projectchallenge.entities.Cart;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {
    private final CartServices cartServices;


    @PostMapping
    public ResponseEntity<List<CartResponse>> addProductToCart(@RequestBody ProductAddToCartRequest productAddToCartRequest){
        return new ResponseEntity<List<CartResponse>>(cartServices.addProductToCart(productAddToCartRequest), HttpStatus.ACCEPTED);
    }
    @DeleteMapping
    public ResponseEntity<Void>deleteProductToCart(@RequestBody ProductRemoveToCartRequest cartRequest){
        cartServices.removeProductFromCart(cartRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long customerId){
        return new ResponseEntity<>(cartServices.getCart(customerId),HttpStatus.OK);
    }
    @PostMapping("/emptyCart/{customerId}")
    public ResponseEntity<Cart>getEmptyCart(@PathVariable Long customerId){
        return new ResponseEntity<>(cartServices.emptyCart(customerId),HttpStatus.CREATED);
    }
}

