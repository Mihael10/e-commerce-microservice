package com.ecommerce.orderservice.write.api.entity;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String order_id;
    private String product_id;
    private String user_id;
    private String address_id;
    private Integer quantity;
    private String order_status;
}
