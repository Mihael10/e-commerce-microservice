package com.ecommerce.sharedservice.commands;

import com.ecommerce.sharedservice.model.CardDetails;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ValidatePaymentCommand {

    @TargetAggregateIdentifier
    private String payment_id;
    private String order_id;
    private CardDetails cardDetails;
}
