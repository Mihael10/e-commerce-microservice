package com.ecommerce.productservice.write.api.aggregation;


import com.ecommerce.productservice.write.api.commands.CreateProductCommand;
import com.ecommerce.productservice.write.api.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ProductAggregation {

    @AggregateIdentifier
    private String product_id;
    private String product_name;
    private double product_price;
    private String product_description;
    private Integer product_quantity;

    public ProductAggregation() {
    }

    @CommandHandler
    public ProductAggregation(CreateProductCommand createProductCommand) {
        //creating all validations and events

        //event
        ProductCreatedEvent productEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productEvent);

        AggregateLifecycle.apply(productEvent);
    }

    //updating the event if there is a change
    @EventSourcingHandler
    public void onEvent(ProductCreatedEvent productEvent){
            this.product_id = productEvent.getProduct_id();
            this.product_quantity = productEvent.getProduct_quantity();
            this.product_price = productEvent.getProduct_price();
            this.product_description = productEvent.getProduct_description();
            this.product_name = productEvent.getProduct_name();
    }
}
