package com.ecommerce.orderservice.write.api.controller;


import com.ecommerce.orderservice.write.api.command.CreateOrderCommand;
import com.ecommerce.orderservice.write.api.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String createOrder(@RequestBody OrderModel orderModel){

        String order_id = UUID.randomUUID().toString();

        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .order_id(order_id)
                .address_id(orderModel.getAddress_id())
                .product_id(orderModel.getProduct_id())
                .quantity(orderModel.getQuantity())
                .user_id(orderModel.getUser_id())
                .order_status("CREATED")
                .build();
        commandGateway.sendAndWait(createOrderCommand);
        return "Order Created";
    }
}
