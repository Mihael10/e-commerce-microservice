package com.ecommerce.productservice.write.api.commands;


import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;



@Data
@Builder
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private String product_id;
    private String product_name;
    private double product_price;
    private String product_description;
    private Integer product_quantity;
}
