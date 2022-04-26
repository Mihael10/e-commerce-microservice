package com.ecommerce.userservice.controller;

import com.ecommerce.sharedservice.model.UserDetails;
import com.ecommerce.sharedservice.queries.GetUserPaymentDetails;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/")
public class UserController {

    private transient QueryGateway queryGateway;

    public UserController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("user/{user_id}")
    public UserDetails getUserPaymentDetails(@PathVariable String user_id){
        GetUserPaymentDetails userPaymentDetails = new GetUserPaymentDetails(user_id);
        UserDetails user = queryGateway.query(userPaymentDetails, ResponseTypes.instanceOf(UserDetails.class)).join();
        return user;
    }

}
