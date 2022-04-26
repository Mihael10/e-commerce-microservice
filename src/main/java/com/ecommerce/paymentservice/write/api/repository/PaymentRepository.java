package com.ecommerce.paymentservice.write.api.repository;

import com.ecommerce.paymentservice.write.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
}
