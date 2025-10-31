package cadsok.payment.messaging.service;

import cadsok.payment.domain.core.event.PaymentFailedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentFailedEventService extends PaymentEventProducerService<PaymentFailedEvent> {

    @Value("${local.kafka-topic-names.payment-failed}")
    private String topicName;

    public PaymentFailedEventService(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
