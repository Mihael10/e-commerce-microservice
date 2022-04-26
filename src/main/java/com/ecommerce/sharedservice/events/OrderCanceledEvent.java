package com.ecommerce.sharedservice.events;

import lombok.Data;

@Data
public class OrderCanceledEvent {

    private String order_id;
    private String order_status;
}
