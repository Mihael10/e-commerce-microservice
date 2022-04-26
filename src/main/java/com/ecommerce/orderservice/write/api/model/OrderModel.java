package com.ecommerce.orderservice.write.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    private String product_id;
    private String user_id;
    private String address_id;
    private Integer quantity;
}
