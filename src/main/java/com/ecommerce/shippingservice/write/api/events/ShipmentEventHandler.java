package com.ecommerce.shippingservice.write.api.events;

import com.ecommerce.sharedservice.events.OrderShippedEvent;
import com.ecommerce.shippingservice.write.api.entity.Shipping;
import com.ecommerce.shippingservice.write.api.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShipmentEventHandler {
    // The event we want to handle which is OrderShippedEvent

    private final ShippingRepository shippingRepository;

    @EventHandler
    public void onEvent(OrderShippedEvent event){
        var shipping = new Shipping();

        BeanUtils.copyProperties(event, shipping);

        shippingRepository.save(shipping);
    }
}
