package com.ecommerce.productservice.write.api.controller;


import com.ecommerce.productservice.write.api.commands.CreateProductCommand;
import com.ecommerce.productservice.write.api.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductWriteController {

    private final CommandGateway commandGateway;

    @PostMapping("/add")
    public String addProduct (@RequestBody ProductModel product) {

        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .product_id((UUID.randomUUID().toString()))
                .product_name(product.getProduct_name())
                .product_price(product.getProduct_price())
                .product_description(product.getProduct_description())
                .product_quantity(product.getProduct_quantity())
                .build();

        String result = commandGateway.sendAndWait(createProductCommand);
        return result;
    }
}
