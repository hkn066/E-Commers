package com.challenge.projectchallenge.controllers;

import com.challenge.projectchallenge.business.abstracts.ProductServices;
import com.challenge.projectchallenge.business.requests.CreateProductRequest;
import com.challenge.projectchallenge.business.requests.UpdateProductRequest;
import com.challenge.projectchallenge.business.responses.ProductResponse;
import com.challenge.projectchallenge.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductServices productServices;

    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
       return new ResponseEntity<>(productServices.getProduct(productId), HttpStatus.FOUND);
    }
    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productServices.getAllProducts(), HttpStatus.FOUND);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(productServices.createProduct(createProductRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest updateProductRequest) {
        return new ResponseEntity<>(productServices.updateProduct(productId,updateProductRequest),HttpStatus.GONE);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productServices.deleteProduct(productId);
        return new ResponseEntity<>("Product Silindi",HttpStatus.ACCEPTED);
    }
}
