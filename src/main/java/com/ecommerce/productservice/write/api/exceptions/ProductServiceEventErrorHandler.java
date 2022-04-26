package com.ecommerce.productservice.write.api.exceptions;

import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

public class ProductServiceEventErrorHandler implements ListenerInvocationErrorHandler {
    @Override
    public void onError(Exception e, EventMessage<?> eventMessage,
                        EventMessageHandler eventMessageHandler) throws Exception {
        throw e;
    }


}
