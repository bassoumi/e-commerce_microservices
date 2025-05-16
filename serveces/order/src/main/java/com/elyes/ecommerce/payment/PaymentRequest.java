package com.elyes.ecommerce.payment;


import com.elyes.ecommerce.customer.CustomerResponse;
import com.elyes.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
