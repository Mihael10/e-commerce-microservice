package com.ecommerce.productservice.write.api.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_service")
public class ProductEntity {

    @Id
    private String product_id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "product_price")
    private double product_price;

    @Column(name = "product_description")
    private String product_description;

    @Column(name = "product_quantity")
    private Integer product_quantity;
}
