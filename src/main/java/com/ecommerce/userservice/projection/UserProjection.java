package com.ecommerce.userservice.projection;

import com.ecommerce.sharedservice.model.CardDetails;
import com.ecommerce.sharedservice.model.UserDetails;
import com.ecommerce.sharedservice.queries.GetUserPaymentDetails;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    @QueryHandler
    public UserDetails getUserPaymentDetails(GetUserPaymentDetails userPaymentDetails){

        //We need to make it to fetch data from DB
        CardDetails cardDetails = CardDetails.builder()
                .card_number("1224512511")
                .cvv(111)
                .card_holder("Miki josi")
                .valid_month(12)
                .valid_year(22)
                .build();

        return UserDetails.builder()
                .user_id(userPaymentDetails.getUser_id())
                .first_name("Miki")
                .last_name("Josi")
                .cardDetails(cardDetails)
                .build();
    }
}
