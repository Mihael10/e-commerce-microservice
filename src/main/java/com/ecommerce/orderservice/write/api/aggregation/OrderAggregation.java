package com.ecommerce.orderservice.write.api.aggregation;

import com.ecommerce.orderservice.write.api.command.CreateOrderCommand;
import com.ecommerce.orderservice.write.api.events.OrderCreatedEvent;
import com.ecommerce.sharedservice.commands.CancelOrderCommand;
import com.ecommerce.sharedservice.commands.CompletedOrderCommand;
import com.ecommerce.sharedservice.events.OrderCanceledEvent;
import com.ecommerce.sharedservice.events.OrderCompletedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregation {

    //in this class we take the data from CreateOrderCommand
    @AggregateIdentifier
    private String order_id;
    private String product_id;
    private String user_id;
    private String address_id;
    private Integer quantity;
    private String order_status;

    public OrderAggregation(){

    }
    @CommandHandler
    public OrderAggregation(CreateOrderCommand createOrderCommand){
       //Validate the command
        var orderCreated = new OrderCreatedEvent();

        BeanUtils.copyProperties(createOrderCommand, orderCreated);
        AggregateLifecycle.apply(orderCreated);
    }

    @EventSourcingHandler
    //updating data
    public void onEvent(OrderCreatedEvent event){
        this.order_status = event.getOrder_status();
        this.user_id = event.getUser_id();
        this.product_id = event.getProduct_id();
        this.address_id = event.getAddress_id();
        this.order_id = event.getOrder_id();
        this.quantity = event.getQuantity();
    }

    @CommandHandler
    public void handleCommand(CompletedOrderCommand completedOrderCommand){
        OrderCompletedEvent orderCompleted = OrderCompletedEvent.builder()
                .order_id(completedOrderCommand.getOrder_id())
                .order_status(completedOrderCommand.getOrder_status())
                .build();

        AggregateLifecycle.apply(orderCompleted);
    }

    @EventSourcingHandler
    public void onEvent(OrderCompletedEvent event){
        this.order_status = event.getOrder_status();

    }

    @CommandHandler
    public void handleCommand(CancelOrderCommand cancelOrder){
        var orderCanceledEvent = new OrderCanceledEvent();

        //Copy Properties
        BeanUtils.copyProperties(cancelOrder, orderCanceledEvent);

        AggregateLifecycle.apply(orderCanceledEvent);
    }
    //For Every Command Created We Need To Handle The Event Also
    @EventSourcingHandler
    public void onEvent(OrderCanceledEvent event){
            this.order_status = event.getOrder_status();
    }
}
