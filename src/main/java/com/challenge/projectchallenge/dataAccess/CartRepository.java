package com.challenge.projectchallenge.dataAccess;

import com.challenge.projectchallenge.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
        Optional<Cart> findByCustomerId(@Param("cid") Long customerId);

    boolean existsByCustomerId(Long customerId);

    void deleteByCustomerId(Long customerId);
}
