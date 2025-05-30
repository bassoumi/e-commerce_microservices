package com.elyes.ecommerce.payment;


import com.elyes.ecommerce.notification.NotificationProducer;
import com.elyes.ecommerce.notification.PaymentNotificationRequest;
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
          new PaymentNotificationRequest(
                  request.orderReference(),
                  request.amount(),
                  request.paymentMethod(),
                  request.customer().firstName(),
                  request.customer().lastName(),
                  request.customer().email()
          )

  );

       return payment.getId();
    }
}
