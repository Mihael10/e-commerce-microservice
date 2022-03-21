package com.ecommerce.productservice.write.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductCreatedEvent {

    private String product_id;
    private String product_name;
    private double product_price;
    private String product_description;
    private Integer product_quantity;
}
