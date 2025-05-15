package com.elyes.ecommerce.kafka;


import com.elyes.ecommerce.customer.CustomerResponse;
import com.elyes.ecommerce.oder.PaymentMethod;
import com.elyes.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
