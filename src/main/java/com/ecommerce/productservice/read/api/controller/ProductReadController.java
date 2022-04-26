package com.ecommerce.productservice.read.api.controller;

import com.ecommerce.productservice.read.api.queries.GetProductQuery;
import com.ecommerce.productservice.write.api.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductReadController {

    private final QueryGateway queryGateway;

    @GetMapping("/all")
    public List<ProductModel> getAllProducts(){

        var getProducts = new GetProductQuery();

        List<ProductModel> listProducts = queryGateway.query(getProducts, ResponseTypes
                .multipleInstancesOf(ProductModel.class)).join();
        return listProducts;
    }
}
