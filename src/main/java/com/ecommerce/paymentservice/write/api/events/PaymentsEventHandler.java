package com.ecommerce.paymentservice.write.api.events;


import com.ecommerce.paymentservice.write.api.entity.Payment;
import com.ecommerce.paymentservice.write.api.repository.PaymentRepository;
import com.ecommerce.sharedservice.events.PaymentCanceledEvent;
import com.ecommerce.sharedservice.events.PaymentProcessedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class PaymentsEventHandler {

    private final PaymentRepository paymentRepository;

    @EventHandler
    public void onEvent(PaymentProcessedEvent event){
        Payment payment = Payment.builder()
                .order_id(event.getOrder_id())
                .payment_id(event.getPayment_id())
                .payment_status("COMPLETED")
                .timeStamp(new Date())
                .build();
        paymentRepository.save(payment);
    }
    @EventHandler
    public void onEvent(PaymentCanceledEvent event){
        // Update
        Payment payment = paymentRepository.findById(event.getPayment_id()).get();
        payment.setPayment_status(event.getPayment_status());

        paymentRepository.save(payment);

    }
}
