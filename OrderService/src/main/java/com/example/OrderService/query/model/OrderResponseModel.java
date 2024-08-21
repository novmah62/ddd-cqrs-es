package com.example.OrderService.query.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseModel {

    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

}
