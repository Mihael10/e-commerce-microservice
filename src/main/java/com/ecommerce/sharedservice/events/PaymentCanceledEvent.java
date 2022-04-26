package com.ecommerce.sharedservice.events;

import lombok.Data;

@Data
public class PaymentCanceledEvent {

    private String payment_id;
    private String order_id;
    private String payment_status;
}
