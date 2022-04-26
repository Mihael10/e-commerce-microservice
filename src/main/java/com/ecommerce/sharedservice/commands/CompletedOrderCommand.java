package com.ecommerce.sharedservice.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompletedOrderCommand {

    @TargetAggregateIdentifier
    private String order_id;
    private String order_status;
}
