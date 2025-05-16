package com.elyes.ecommerce.order;

import com.elyes.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,

        @Positive(message = "order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "customerId method should be precised")
        @NotEmpty(message = "customerId method should be precised")
        @NotBlank(message = "customerId method should be precised")
        String customerId,

        @NotEmpty(message = " products method should be empty")
        List<PurchaseRequest> products

) {
}
