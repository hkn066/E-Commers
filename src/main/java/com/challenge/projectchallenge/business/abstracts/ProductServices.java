package com.challenge.projectchallenge.business.abstracts;

import com.challenge.projectchallenge.business.requests.CreateProductRequest;
import com.challenge.projectchallenge.business.requests.UpdateProductRequest;
import com.challenge.projectchallenge.business.responses.ProductResponse;
import com.challenge.projectchallenge.entities.Product;

import java.util.List;

public interface ProductServices {
    ProductResponse getProduct(Long productId);
    ProductResponse createProduct(CreateProductRequest productRequest);
    ProductResponse updateProduct(Long productId, UpdateProductRequest updateProductRequest);
    void deleteProduct(Long productId);

    List<ProductResponse> getAllProducts();
}
