package com.ecommerce.productservice.write.api.exceptions;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;

public class ExceptionConfig {

    @Autowired
    public void configure(EventProcessingConfigurer configurer){
        configurer.registerListenerInvocationErrorHandler(
                "product", configuration -> new ProductServiceEventErrorHandler()
        );

    }
}
