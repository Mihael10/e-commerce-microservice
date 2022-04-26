package com.ecommerce.sharedservice.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCompletedEvent {

    private String order_id;
    private String order_status;
}
