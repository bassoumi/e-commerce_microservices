package com.elyes.ecommerce.order;


import com.elyes.ecommerce.oderLine.OrderLine;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {
    public Order toOrderWithLines(OrderRequest request) {
        if (request == null) return null;

        Order order = Order.builder()
                .id(request.id())
                .reference(request.reference())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .amount(request.amount())
                .build();

        List<OrderLine> lines = request.products().stream()
                .map(product -> OrderLine.builder()
                        .productId(product.productId())
                        .quantity(product.quantity())
                        .order(order) // IMPORTANT: link back to parent
                        .build())
                .collect(Collectors.toList());

        order.setOrderLines(lines);
        return order;
    }


    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                // use the new getter name:
                order.getAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
