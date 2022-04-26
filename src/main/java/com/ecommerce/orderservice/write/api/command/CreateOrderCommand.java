package com.ecommerce.orderservice.write.api.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateOrderCommand {

    // This class holds the payloads of data that we want to process to the controller
    @TargetAggregateIdentifier // order_id will be the unique key
    private String order_id;
    private String product_id;
    private String user_id;
    private String address_id;
    private Integer quantity;
    private String order_status;
}
