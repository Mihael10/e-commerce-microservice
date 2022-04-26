package com.ecommerce.orderservice.write.api.events;

import com.ecommerce.orderservice.write.api.entity.Order;
import com.ecommerce.orderservice.write.api.repository.OrderRepository;
import com.ecommerce.sharedservice.events.OrderCanceledEvent;
import com.ecommerce.sharedservice.events.OrderCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventsHandler {

    private final OrderRepository orderRepository;

    @EventHandler
    public void onEvent(OrderCreatedEvent event){
        var order= new Order();
        BeanUtils.copyProperties(event, order);
        orderRepository.save(order);
    }

    @EventHandler
    public void onEvent(OrderCompletedEvent event){
        var order = orderRepository.findById(event.getOrder_id()).get();
            order.setOrder_status(event.getOrder_status());
            orderRepository.save(order);
    }

    //Update Event
    @EventHandler
    public void onEvent(OrderCanceledEvent event){
        var order = orderRepository.findById(event.getOrder_id()).get();
        order.setOrder_status(event.getOrder_status());
        orderRepository.save(order);
    }
}
