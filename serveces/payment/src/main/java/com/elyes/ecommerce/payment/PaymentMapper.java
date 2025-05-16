package com.elyes.ecommerce.payment;


import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(@Valid paymentRequest request) {
      return Payment.builder()
              .id(request.id())
              .orderId(request.orderId())
              .paymentMethod(request.paymentMethod())
              .amount(request.amount())
              .build();

    }
}
