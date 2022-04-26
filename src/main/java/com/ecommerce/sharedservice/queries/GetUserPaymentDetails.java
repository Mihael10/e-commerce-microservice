package com.ecommerce.sharedservice.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPaymentDetails {

    //to fetch the payment details from user_id
    // this query will be handled by user-service
    private String user_id;
}
