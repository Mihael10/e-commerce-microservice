package com.ecommerce.orderservice.write.api.events;

import lombok.Data;

@Data
public class OrderCreatedEvent {
    private String order_id;
    private String product_id;
    private String user_id;
    private String address_id;
    private Integer quantity;
    private String order_status;
}
