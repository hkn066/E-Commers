package com.challenge.projectchallenge.business.concrets;

import com.challenge.projectchallenge.business.abstracts.CartServices;
import com.challenge.projectchallenge.business.abstracts.OrderServices;
import com.challenge.projectchallenge.dataAccess.CartItemRepository;
import com.challenge.projectchallenge.dataAccess.OrderItemRepository;
import com.challenge.projectchallenge.dataAccess.OrderRepository;
import com.challenge.projectchallenge.dataAccess.ProductRepository;
import com.challenge.projectchallenge.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderServices {
    private final OrderRepository orderRepository;
    private final CartServices cartServices;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Override
    public Order placeOrder(Long customerId) {
        Cart cart = cartServices.getCart(customerId);
        Order order = addOrder(cart);
        List<OrderItem> orderItems = order.getItems()
                .stream()
                .map(orderItemRepository::save)
                .toList();
        order.setItems(orderItems);
        orderRepository.save(order);
        cartServices.emptyCart(customerId);
        return order;
    }


    @Override
    public Order getOrder(Long orderId) {
        try {
            return orderRepository.findById(orderId).orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    private Order addOrder(Cart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        List<Long> cartItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            Product product = productRepository.findById(cartItem.getProduct().getId()).orElseThrow();
            if (product.getStockQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Yeterli stok bulunmamaktadır.");
            }
            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.save(product);
            OrderItem orderItem = OrderItem.builder().product(cartItem.getProduct()).quantity(cartItem.getQuantity()).totalPrice(cartItem.getTotalPrice()).build();
            orderItems.add(orderItem);
            cartItems.add(cartItem.getId());

        }
        cartItemRepository.deleteAllByIdInBatch(cartItems);
        return Order.builder().items(orderItems).totalPrice(cart.getTotalPrice()).customer(cart.getCustomer()).build();
    }
}


