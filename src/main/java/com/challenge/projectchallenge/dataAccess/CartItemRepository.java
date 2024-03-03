package com.challenge.projectchallenge.dataAccess;

import com.challenge.projectchallenge.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findByCartIdAndProductId(Long cartId, Long id);
}
