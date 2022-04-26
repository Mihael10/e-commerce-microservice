package com.ecommerce.sharedservice.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetails {

    private String card_holder;
    private String card_number;
    private Integer valid_month;
    private Integer valid_year;
    private Integer cvv;
}
