package com.ecommerce.productservice.write.api.events;


import com.ecommerce.productservice.write.api.repository.ProductRepository;
import com.ecommerce.productservice.write.api.repository.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventHandler {


    private final ProductRepository productRepository;

    @EventHandler
    public void onCreatedEvent(ProductCreatedEvent productCreatedEvent){

        var product = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productRepository);
        productRepository.save(product);
    }
}
