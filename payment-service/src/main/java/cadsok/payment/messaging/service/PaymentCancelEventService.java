package cadsok.payment.messaging.service;

import cadsok.payment.domain.core.event.PaymentCancelledEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentCancelEventService extends PaymentEventProducerService<PaymentCancelledEvent> {

    @Value("${local.kafka-topic-names.payment-cancel}")
    private String topicName;

    public PaymentCancelEventService(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
