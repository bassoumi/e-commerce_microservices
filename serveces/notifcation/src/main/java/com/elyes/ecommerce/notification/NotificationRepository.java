package com.elyes.ecommerce.notification;

import com.elyes.ecommerce.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
