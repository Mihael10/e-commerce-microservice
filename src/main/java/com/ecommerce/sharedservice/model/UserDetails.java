package com.ecommerce.sharedservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetails {

    private String user_id;
    private String first_name;
    private String last_name;
    private CardDetails cardDetails;
}
