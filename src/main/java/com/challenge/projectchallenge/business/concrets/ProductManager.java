package com.challenge.projectchallenge.business.concrets;

import com.challenge.projectchallenge.business.abstracts.ProductServices;
import com.challenge.projectchallenge.business.requests.CreateProductRequest;
import com.challenge.projectchallenge.business.requests.UpdateProductRequest;
import com.challenge.projectchallenge.business.responses.ProductResponse;
import com.challenge.projectchallenge.core.utilities.mappers.ModelMapperService;
import com.challenge.projectchallenge.dataAccess.ProductRepository;
import com.challenge.projectchallenge.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductServices {
    private final ProductRepository productRepository;
    private final ModelMapperService mapperService;

    @Override
    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        return mapperService.forResponse().map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest productRequest) {
        Product product = mapperService.forRequest().map(productRequest, Product.class);
        productRepository.save(product);
        return mapperService.forResponse().map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse updateProduct(Long productId, UpdateProductRequest updateProductRequest) {
        productRepository.findById(productId).orElseThrow(RuntimeException::new);
        Product product = mapperService.forRequest().map(updateProductRequest, Product.class);
        product.setId(productId);
        productRepository.save(product);
        return mapperService.forResponse().map(product, ProductResponse.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        productRepository.delete(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productResponse -> mapperService.forResponse().map(productResponse, ProductResponse.class)).toList();
    }
}
