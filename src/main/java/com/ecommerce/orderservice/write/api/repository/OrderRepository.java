package com.ecommerce.orderservice.write.api.repository;

import com.ecommerce.orderservice.write.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
