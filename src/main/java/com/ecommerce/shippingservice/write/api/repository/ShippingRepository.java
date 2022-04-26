package com.ecommerce.shippingservice.write.api.repository;

import com.ecommerce.shippingservice.write.api.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, String> {
}
