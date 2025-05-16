package com.elyes.ecommerce.notification;


import com.elyes.ecommerce.payment.Payment;
import com.elyes.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotifcationRequest(
        String orderReference,
        BigDecimal amount ,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail

        ) {
}
