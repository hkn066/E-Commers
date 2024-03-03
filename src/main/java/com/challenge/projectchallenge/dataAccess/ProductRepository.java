package com.challenge.projectchallenge.dataAccess;

import com.challenge.projectchallenge.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
