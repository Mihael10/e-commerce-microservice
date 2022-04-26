package com.ecommerce.shippingservice.write.api.aggregation;

import com.ecommerce.sharedservice.commands.ShippingOrderCommand;
import com.ecommerce.sharedservice.events.OrderShippedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


@Aggregate
public class ShippingAggregation {

    @AggregateIdentifier
    private String shipping_id;
    private String order_id;
    private String shipping_status;

    @CommandHandler
    public ShippingAggregation(ShippingOrderCommand shippingOrderCommand) {

        OrderShippedEvent orderShippedEvent= OrderShippedEvent.builder()
                .shipping_id(shippingOrderCommand.getShipping_id())
                .order_id(shippingOrderCommand.getOrder_id())
                .shipping_status("ORDER COMPLETED")
                .build();

        AggregateLifecycle.apply(orderShippedEvent);
    }

    @EventSourcingHandler
    public void onEvent(OrderShippedEvent event){
        this.order_id = event.getOrder_id();
        this.shipping_id = event.getShipping_id();
        this.shipping_status = event.getShipping_status();
    }
}
