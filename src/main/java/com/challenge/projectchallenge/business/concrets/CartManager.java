package com.challenge.projectchallenge.business.concrets;

import com.challenge.projectchallenge.business.abstracts.CartServices;
import com.challenge.projectchallenge.business.requests.ProductAddToCartRequest;
import com.challenge.projectchallenge.business.requests.ProductRemoveToCartRequest;
import com.challenge.projectchallenge.business.requests.UpdateProductToCart;
import com.challenge.projectchallenge.business.responses.CartResponse;
import com.challenge.projectchallenge.core.utilities.mappers.ModelMapperService;
import com.challenge.projectchallenge.dataAccess.CartItemRepository;
import com.challenge.projectchallenge.dataAccess.CartRepository;
import com.challenge.projectchallenge.dataAccess.CustomerRepository;
import com.challenge.projectchallenge.dataAccess.ProductRepository;
import com.challenge.projectchallenge.entities.Cart;
import com.challenge.projectchallenge.entities.CartItem;
import com.challenge.projectchallenge.entities.Customer;
import com.challenge.projectchallenge.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartManager implements CartServices {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapperService mapperService;

    @Override
    public Cart getCart(Long customerId) {
        try {
            return cartRepository.findByCustomerId(customerId).orElseThrow(Exception::new);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cart emptyCart(Long customerId) {
        Cart existingCart = cartRepository.findByCustomerId(customerId).orElseThrow();
        existingCart.setTotalPrice(0.0);
        existingCart.setItems(null);
        return cartRepository.save(existingCart);
    }

    @Override
    public void updateProductToCart(UpdateProductToCart updateProductToCart) {
        Product product = productRepository.findById(updateProductToCart.getProductId()).orElseThrow(RuntimeException::new);
        Customer customer = customerRepository.findById(updateProductToCart.getCustomerId()).orElseThrow(RuntimeException::new);
        Cart cart = cartRepository.findByCustomerId(customer.getId()).orElseThrow();
        CartItem existingCartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(updateProductToCart.getQuantity());
            existingCartItem.setTotalPrice(product.getPrice() * existingCartItem.getQuantity());
            cartItemRepository.save(existingCartItem);
        }
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getTotalPrice).sum());
        cartRepository.save(cart);
    }

    @Override
    public List<CartResponse> addProductToCart(ProductAddToCartRequest productAddToCartRequest) {
        Product product = productRepository.findById(productAddToCartRequest.getProductId()).orElseThrow(RuntimeException::new);
        Customer customer = customerRepository.findById(productAddToCartRequest.getCustomerId()).orElseThrow(RuntimeException::new);
        Cart cart = cartRepository.findByCustomerId(customer.getId()).orElseThrow();
        CartItem cartItem = mapperService.forRequest().map(productAddToCartRequest, CartItem.class);

        cartItem.setCart(cart);
        cartItem.setTotalPrice(cartItem.getQuantity() * product.getPrice());
        cartItemRepository.save(cartItem);
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getTotalPrice).sum());
        cartRepository.save(cart);
        return productInCart(cart);

    }

    @Override
    public void removeProductFromCart(ProductRemoveToCartRequest cartRequest) {
        Customer customer = customerRepository.findById(cartRequest.getCustomerId()).orElseThrow(RuntimeException::new);
        Product product = productRepository.findById(cartRequest.getProductId()).orElseThrow(RuntimeException::new);
        Cart cart = cartRepository.findByCustomerId(customer.getId()).orElseThrow();
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());
        cartItemRepository.delete(cartItem);
        cart.setTotalPrice(cart.getItems().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum());
        cartRepository.save(cart);
    }


    private List<CartResponse> productInCart(Cart cart) {
        CartResponse cartResponse = mapperService.forResponse().map(cart, CartResponse.class);
        List<CartResponse> productList = cart.getItems().stream()
                .map(item -> mapperService.forResponse().map(item, CartResponse.class))
                .toList();
        for (CartResponse crtResponse : productList) {
            cartResponse.setProductName(crtResponse.getProductName());
            cartResponse.setQuantity(crtResponse.getQuantity());
            cartResponse.setProductId(crtResponse.getProductId());

        }
        return productList;
    }
}
