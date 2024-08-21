package com.example.OrderService.command.controller;

import com.example.CommonService.commands.CreateOrderCommand;
import com.example.OrderService.command.model.OrderRequestModel;
import com.fasterxml.jackson.databind.JsonNode;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    private final CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderRequestModel orderRequestModel) {
        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .productId(orderRequestModel.getProductId())
                .userId(orderRequestModel.getUserId())
                .addressId(orderRequestModel.getAddressId())
                .quantity(orderRequestModel.getQuantity())
                .orderStatus("CREATED").build();

        commandGateway.sendAndWait(createOrderCommand);
        return "Order Created";
    }

}
