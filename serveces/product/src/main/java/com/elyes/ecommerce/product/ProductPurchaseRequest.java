package com.elyes.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,

        @NotNull(message = "quantity is mandatory")
        double quantity
) {
}
