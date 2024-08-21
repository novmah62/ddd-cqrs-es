package com.example.UserService.query.projection;

import com.example.CommonService.model.CardDetails;
import com.example.CommonService.model.User;
import com.example.CommonService.queries.GetUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
        CardDetails cardDetails = CardDetails.builder()
                .name("Nguyen Van A")
                .validUntilYear(2024)
                .validUntilMonth(8)
                .cardNumber("123456789")
                .cvv(111).build();

        return User.builder()
                .userId(query.getUserId())
                .firstName("A")
                .lastName("Nguyen Van")
                .cardDetails(cardDetails).build();
    }

}
