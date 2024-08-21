package com.example.CommonService.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProcessedEvent {

    private String paymentId;
    private String orderId;

}
