package com.ecommerce.sharedservice.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderShippedEvent {

    private String shipping_id;
    private String order_id;
    private String shipping_status;
}
