package com.ecommerce.shippingservice.write.api.entity;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class Shipping {

    @Id
    private String shipping_id;
    private String order_id;
    private String shipping_status;
}
