package com.elyes.ecommerce.oderLine;


import com.elyes.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {

        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(Order.builder()
                        .id(request.id())
                        .build())
                .productId(request.productId())
                .build();
    }

    public OrderLineeResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineeResponse(orderLine.getId(),orderLine.getQuantity());
    }
}
