package com.elyes.ecommerce.payment;


import com.elyes.ecommerce.notification.NotificationProducer;
import com.elyes.ecommerce.notification.PaymentNotifcationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private  final paymentRepository repository;
    private final PaymentMapper mapper;
    private final  NotificationProducer notificationProducer;

    public Integer createPayment(@Valid paymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));
  notificationProducer.send(
          new PaymentNotifcationRequest(
                  request.orderReference(),
                  request.amount(),
                  request.paymentMethod(),
                  request.customer().firstname(),
                  request.customer().lastname(),
                  request.customer().email()
          )

  );

       return payment.getId();
    }
}
