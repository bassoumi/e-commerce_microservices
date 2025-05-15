package com.elyes.ecommerce.oder;


import com.elyes.ecommerce.customer.CustomerClient;
import com.elyes.ecommerce.exception.BuisnessException;
import com.elyes.ecommerce.kafka.OrderConfirmation;
import com.elyes.ecommerce.kafka.OrderProducer;
import com.elyes.ecommerce.oderLine.OrderLineRequest;
import com.elyes.ecommerce.oderLine.OrderLineService;
import com.elyes.ecommerce.product.ProductClient;
import com.elyes.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final OrderMapper mapper;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public Integer createOrder( OrderRequest request) {

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BuisnessException("cannot create order , no customer exsist with the provided id"+ request.customerId()));

       var purchaseProducts =  this.productClient.purchaseProducts(request.products());


        var order = this.repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()

                    )


            );

        }

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts

                )
        );



        return  order.getId();


    }

    public List<OrderResponse> findAll() {
        return  repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No order found with the provided ID %d", orderId)
                ));
    }

}
