package com.example.OrderService.query.controller;

import com.example.OrderService.query.model.OrderResponseModel;
import com.example.OrderService.query.queries.GetAllOrdersQuery;
import com.example.OrderService.query.queries.GetOrderByIdQuery;
import com.example.OrderService.query.queries.GetOrdersByUserIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderQueryController {

    private final QueryGateway queryGateway;

    public OrderQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<OrderResponseModel> getAllOrders() {
        GetAllOrdersQuery getAllOrdersQuery = new GetAllOrdersQuery();
        return queryGateway.query(getAllOrdersQuery,
                ResponseTypes.multipleInstancesOf(OrderResponseModel.class))
                .join();
    }

    @GetMapping("/{orderId}")
    public OrderResponseModel getOrderById(@PathVariable String orderId) {
        GetOrderByIdQuery getOrderByIdQuery = new GetOrderByIdQuery(orderId);
        return queryGateway.query(getOrderByIdQuery,
                ResponseTypes.instanceOf(OrderResponseModel.class))
                .join();
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponseModel> getOrdersByUserId(@PathVariable String userId) {
        GetOrdersByUserIdQuery getOrdersByUserIdQuery = new GetOrdersByUserIdQuery(userId);
        return queryGateway.query(getOrdersByUserIdQuery,
                        ResponseTypes.multipleInstancesOf(OrderResponseModel.class))
                .join();
    }

}
