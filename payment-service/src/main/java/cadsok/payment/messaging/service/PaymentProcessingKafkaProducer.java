package cadsok.payment.messaging.service;

import cadsok.payment.domain.core.event.PaymentProcessingEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessingKafkaProducer extends PaymentEventProducer<PaymentProcessingEvent> {

    @Value("${kafka.topic-names.payment-processing}")
    private String topicName;

    public PaymentProcessingKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
