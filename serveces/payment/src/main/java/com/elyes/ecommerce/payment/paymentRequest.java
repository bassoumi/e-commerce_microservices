package com.elyes.ecommerce.payment;


import java.math.BigDecimal;

public record paymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer

) {
}
