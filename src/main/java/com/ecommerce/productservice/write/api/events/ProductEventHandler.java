package com.ecommerce.productservice.write.api.events;


import com.ecommerce.productservice.write.api.repository.ProductRepository;
import com.ecommerce.productservice.write.api.repository.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
@RequiredArgsConstructor
public class ProductEventHandler {


    private final ProductRepository productRepository;

    @EventHandler
    public void onCreatedEvent(ProductCreatedEvent productCreatedEvent) throws Exception {

        var product = new ProductEntity();
        product.setProduct_id(productCreatedEvent.getProduct_id());
        product.setProduct_description(productCreatedEvent.getProduct_description());
        product.setProduct_name(productCreatedEvent.getProduct_name());
        product.setProduct_price(productCreatedEvent.getProduct_price());
        product.setProduct_quantity(product.getProduct_quantity());
        //BeanUtils.copyProperties(productCreatedEvent, product);
        productRepository.save(product);
        throw new Exception("Event not created");
    }

    @ExceptionHandler
    public void handle(Exception e)throws Exception{
        throw e;
    }
}
