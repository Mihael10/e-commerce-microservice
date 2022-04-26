package com.ecommerce.sharedservice.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ShippingOrderCommand {

    @TargetAggregateIdentifier
    private String shipping_id;
    private String order_id;
}
