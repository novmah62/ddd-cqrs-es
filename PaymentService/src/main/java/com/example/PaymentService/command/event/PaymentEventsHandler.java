package com.example.PaymentService.command.event;

import com.example.CommonService.events.PaymentCancelledEvent;
import com.example.CommonService.events.PaymentProcessedEvent;
import com.example.PaymentService.command.data.Payment;
import com.example.PaymentService.command.data.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentEventsHandler {

    private final PaymentRepository paymentRepository;

    public PaymentEventsHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        paymentRepository.save(Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus("COMPLETED")
                .timeStamp(new Date()).build());
    }

    @EventHandler
    public void on(PaymentCancelledEvent event) {
        Payment payment = paymentRepository.findById(event.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found with payment ID: " + event.getPaymentId()));
        payment.setPaymentStatus(event.getPaymentStatus());
        paymentRepository.save(payment);
    }

}
