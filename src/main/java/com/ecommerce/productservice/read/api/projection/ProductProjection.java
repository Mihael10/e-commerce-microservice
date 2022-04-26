package com.ecommerce.productservice.read.api.projection;

import com.ecommerce.productservice.read.api.queries.GetProductQuery;
import com.ecommerce.productservice.write.api.model.ProductModel;
import com.ecommerce.productservice.write.api.repository.ProductRepository;
import com.ecommerce.productservice.write.api.repository.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductProjection {

    private final ProductRepository productRepository;

    public List<ProductModel> handle(GetProductQuery getProducts){

        List<ProductEntity> products = productRepository.findAll();

        List<ProductModel> productModel = products.stream()
                .map(product -> ProductModel.builder()
                        .product_quantity(product.getProduct_quantity())
                        .product_name(product.getProduct_name())
                        .product_description(product.getProduct_description())
                        .product_price(product.getProduct_price())
                        .build()).collect(Collectors.toList());
        return productModel;
    }
}
