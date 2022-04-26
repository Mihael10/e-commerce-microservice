package com.ecommerce.sharedservice.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CancelOrderCommand {

    @TargetAggregateIdentifier
    private String order_id;
    private String order_status = "CANCELED";
}
