package com.ecommerce.productservice.write.api.model;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;



@Data
@Builder
public class ProductModel {

    private String product_name;
    private double product_price;
    private String product_description;
    private Integer product_quantity;
}
