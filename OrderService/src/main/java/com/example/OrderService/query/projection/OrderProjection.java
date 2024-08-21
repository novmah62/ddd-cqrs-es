package com.example.OrderService.query.projection;

import com.example.OrderService.command.data.Order;
import com.example.OrderService.command.data.OrderRepository;
import com.example.OrderService.query.model.OrderResponseModel;
import com.example.OrderService.query.queries.GetAllOrdersQuery;
import com.example.OrderService.query.queries.GetOrderByIdQuery;
import com.example.OrderService.query.queries.GetOrdersByUserIdQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProjection {

    private final OrderRepository orderRepository;

    public OrderProjection(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @QueryHandler
    public List<OrderResponseModel> handle(GetAllOrdersQuery getAllOrdersQuery) {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> OrderResponseModel.builder()
                        .productId(order.getProductId())
                        .userId(order.getUserId())
                        .quantity(order.getQuantity())
                        .addressId(order.getAddressId())
                        .orderStatus(order.getOrderStatus()).build())
                .toList();

    }

    @QueryHandler
    public OrderResponseModel handle(GetOrderByIdQuery getOrderByIdQuery) {
        Order order = orderRepository.findById(getOrderByIdQuery.getOrderId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return OrderResponseModel.builder()
                .productId(order.getProductId())
                .userId(order.getUserId())
                .quantity(order.getQuantity())
                .addressId(order.getAddressId())
                .orderStatus(order.getOrderStatus()).build();
    }

    @QueryHandler
    public List<OrderResponseModel> handle(GetOrdersByUserIdQuery getOrdersByUserIdQuery) {
        List<Order> orders = orderRepository.findByUserId(getOrdersByUserIdQuery.getUserId());
        return orders.stream()
                .map(order -> OrderResponseModel.builder()
                        .productId(order.getProductId())
                        .userId(order.getUserId())
                        .quantity(order.getQuantity())
                        .addressId(order.getAddressId())
                        .orderStatus(order.getOrderStatus()).build())
                .toList();

    }

}
