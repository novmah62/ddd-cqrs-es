package com.example.ProductService.query.projection;

import com.example.ProductService.command.data.Product;
import com.example.ProductService.command.data.ProductRepository;
import com.example.ProductService.command.model.ProductRequestModel;
import com.example.ProductService.query.model.ProductResponseModel;
import com.example.ProductService.query.queries.GetAllProductsQuery;
import com.example.ProductService.query.queries.GetProductByIdQuery;
import com.example.ProductService.query.queries.GetProductsByNameQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductProjection {

    private final ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductResponseModel> handle(GetAllProductsQuery getAllProductsQuery) {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> ProductResponseModel.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity()).build())
                .toList();
    }

    @QueryHandler
    public ProductResponseModel handle(GetProductByIdQuery getProductByIdQuery) {
        Product product = productRepository.findById(getProductByIdQuery.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductResponseModel.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity()).build();
    }

    @QueryHandler
    public List<ProductResponseModel> handle(GetProductsByNameQuery getProductsByNameQuery) {
        List<Product> products = productRepository.findByNameContaining(getProductsByNameQuery.getName());
        return products.stream()
                .map(product -> ProductResponseModel.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity()).build())
                .toList();
    }

}
