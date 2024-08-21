package com.example.ProductService.command.controller;

import com.example.ProductService.command.commands.CreateProductCommand;
import com.example.ProductService.command.commands.DeleteProductCommand;
import com.example.ProductService.command.commands.UpdateProductCommand;
import com.example.ProductService.command.model.ProductRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private final CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRequestModel productRequestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .name(productRequestModel.getName())
                .price(productRequestModel.getPrice())
                .quantity(productRequestModel.getQuantity()).build();
        return commandGateway.sendAndWait(createProductCommand);
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable String productId,
                                @RequestBody ProductRequestModel productRequestModel) {
        UpdateProductCommand updateProductCommand = UpdateProductCommand.builder()
                .productId(productId)
                .name(productRequestModel.getName())
                .price(productRequestModel.getPrice())
                .quantity(productRequestModel.getQuantity()).build();
        return commandGateway.sendAndWait(updateProductCommand);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        DeleteProductCommand deleteProductCommand = DeleteProductCommand.builder()
                .productId(productId).build();
        return commandGateway.sendAndWait(deleteProductCommand);
    }

}
