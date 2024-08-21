package com.example.OrderService.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestModel {

    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;

}
