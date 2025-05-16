package com.elyes.ecommerce.notification;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotificationProducer {

    private final KafkaTemplate<String, PaymentNotifcationRequest> kafkaTemplate;

    public void send(PaymentNotifcationRequest request) {
        log.info("Sending notification request: " + request);
        Message<PaymentNotifcationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "notification")
                .build();
        kafkaTemplate.send(message);
    }
}
