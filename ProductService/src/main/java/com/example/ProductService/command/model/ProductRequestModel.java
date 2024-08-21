package com.example.ProductService.command.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductRequestModel {

    private String name;
    private BigDecimal price;
    private Integer quantity;

}
