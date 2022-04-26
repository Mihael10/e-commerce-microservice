package com.ecommerce.paymentservice.write.api.aggregation;

import com.ecommerce.sharedservice.commands.CancelPaymentCommand;
import com.ecommerce.sharedservice.commands.ValidatePaymentCommand;
import com.ecommerce.sharedservice.events.PaymentCanceledEvent;
import com.ecommerce.sharedservice.events.PaymentProcessedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Slf4j
public class PaymentAggregation {

    @AggregateIdentifier
    private String payment_id;
    private String order_id;
    private String payment_status;

    public PaymentAggregation() {
    }

    @CommandHandler
    public PaymentAggregation(ValidatePaymentCommand validatePaymentCommand) {
        //Validate payment details, and if success publish the payment process event

        log.info("Executing payment validation command for"
                        + "payment_id: {} and payment_id: {}",
                validatePaymentCommand.getPayment_id(),
                validatePaymentCommand.getOrder_id());

        PaymentProcessedEvent paymentProcessedEvent =
                new PaymentProcessedEvent(validatePaymentCommand.getPayment_id(),
                validatePaymentCommand.getOrder_id());

        AggregateLifecycle.apply(paymentProcessedEvent);
        log.info("Payment Applied Successfully!");
    }
    @EventSourcingHandler
    public void onEvent(PaymentProcessedEvent event){
        this.payment_id = event.getPayment_id();
        this.order_id= event.getOrder_id();

    }

    @CommandHandler
    public void handleEvent(CancelPaymentCommand cancelPaymentCommand){
        PaymentCanceledEvent cancelPayment = new PaymentCanceledEvent();
        BeanUtils.copyProperties(cancelPaymentCommand, cancelPayment);

        AggregateLifecycle.apply(cancelPayment);
    }

    @EventSourcingHandler
    public void onEvent(PaymentCanceledEvent event){
        this.payment_status = event.getPayment_status();

    }
}
