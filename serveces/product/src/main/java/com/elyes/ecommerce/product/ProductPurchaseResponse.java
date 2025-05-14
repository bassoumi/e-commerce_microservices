package com.elyes.ecommerce.product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer prodcutId,
        String name,
        String description,
        BigDecimal price,
        double quantity


) {
}
