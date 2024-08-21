package com.example.ProductService.query.controller;

import com.example.ProductService.query.model.ProductResponseModel;
import com.example.ProductService.query.queries.GetAllProductsQuery;
import com.example.ProductService.query.queries.GetProductByIdQuery;
import com.example.ProductService.query.queries.GetProductsByNameQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductResponseModel> getAllProducts() {
        GetAllProductsQuery getAllProductsQuery = new GetAllProductsQuery();
        return queryGateway.query(getAllProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductResponseModel.class))
                .join();
    }

    @GetMapping("/{productId}")
    public ProductResponseModel getProductById(@PathVariable String productId) {
        GetProductByIdQuery getProductByIdQuery = new GetProductByIdQuery(productId);
        return queryGateway.query(getProductByIdQuery,
                ResponseTypes.instanceOf(ProductResponseModel.class))
                .join();
    }

    @GetMapping("/name/{name}")
    public List<ProductResponseModel> getProductsByName(@PathVariable String name) {
        GetProductsByNameQuery getProductsByNameQuery = new GetProductsByNameQuery(name);
        return queryGateway.query(getProductsByNameQuery,
                        ResponseTypes.multipleInstancesOf(ProductResponseModel.class))
                .join();
    }

}