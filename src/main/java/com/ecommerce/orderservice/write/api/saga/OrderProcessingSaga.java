package com.ecommerce.orderservice.write.api.saga;

import com.ecommerce.orderservice.write.api.events.OrderCreatedEvent;
import com.ecommerce.sharedservice.commands.*;
import com.ecommerce.sharedservice.commands.CancelPaymentCommand;
import com.ecommerce.sharedservice.events.*;
import com.ecommerce.sharedservice.model.UserDetails;
import com.ecommerce.sharedservice.queries.GetUserPaymentDetails;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


@Saga
@Slf4j
public class OrderProcessingSaga {

    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;


    public OrderProcessingSaga() {
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "order_id")
    private void handle(OrderCreatedEvent event){
        log.info("Order created event in saga for order_id {}"
         + event.getOrder_id());

        GetUserPaymentDetails getUserPaymentDetails = new GetUserPaymentDetails(event.getUser_id());


        UserDetails user = UserDetails.builder().build();
        try{
            user = queryGateway.query(
                    getUserPaymentDetails, ResponseTypes.instanceOf(UserDetails.class)
            ).join();
        }catch(Exception e){
            log.error(e.getMessage());
            //Start the transaction
            cancelOrder(event.getOrder_id());
        }
       ValidatePaymentCommand validatePaymentCommand = ValidatePaymentCommand.builder()
               .cardDetails(user.getCardDetails())
               .order_id(event.getOrder_id())
               .payment_id(UUID.randomUUID().toString())
               .build();
        commandGateway.sendAndWait(validatePaymentCommand);
    }

    private void cancelOrder(String order_id) {

        var cancelOrderCommand = new CancelOrderCommand(order_id);
            commandGateway.send(cancelOrderCommand);
    }

    @SagaEventHandler(associationProperty = "order_id")
    private void handleEvent(PaymentProcessedEvent event){

        log.info("Order created event in saga for order_id {} "
                + event.getOrder_id());

        try {

            if(true)
                throw new IllegalArgumentException("Something went wrong in Payment Processing Event");


            ShippingOrderCommand shippingOrderCommand = ShippingOrderCommand.builder()
                    .shipping_id(UUID.randomUUID().toString())
                    .order_id(event.getOrder_id())
                    .build();
            commandGateway.send(shippingOrderCommand);
        } catch (Exception e) {
            log.error(e.getMessage());

            cancelPaymentCommand(event);
        }
    }

    private void cancelPaymentCommand(PaymentProcessedEvent event) {

        CancelPaymentCommand cancelPayment = new CancelPaymentCommand(event.getPayment_id(), event.getOrder_id());
        commandGateway.send(cancelPayment);
    }

    @SagaEventHandler(associationProperty = "order_id")
    public void handleEvent(OrderShippedEvent event){

        log.info("Order shipped created event in saga for order_id {} "
                + event.getOrder_id());

        CompletedOrderCommand completedOrder = CompletedOrderCommand.builder()
                .order_id(event.getOrder_id())
                .order_status("APPROVED ORDER")
                .build();

        commandGateway.send(completedOrder);
    }

    @SagaEventHandler(associationProperty = "order_id")
    @EndSaga
    public void handleEvent(OrderCompletedEvent event){

        log.info("Order completed event in saga for order_id {} "
                + event.getOrder_id());
    }
    @SagaEventHandler(associationProperty = "order_id")
    public void handleEvent(OrderCanceledEvent event){

        log.info("Order canceled event in saga for order_id {} "
                + event.getOrder_id());
    }
    @SagaEventHandler(associationProperty = "order_id")
    public void handleEvent(PaymentCanceledEvent event){

        log.info("Payment canceled event in saga for order_id {} "
                + event.getOrder_id());
        cancelOrder(event.getOrder_id());
    }
}
