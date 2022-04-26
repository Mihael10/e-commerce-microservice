package com.ecommerce.sharedservice.commands;

import lombok.Data;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CancelPaymentCommand {

    @TargetAggregateIdentifier
    private String payment_id;
    private String order_id;
    private String payment_status = "CANCELED";

}
