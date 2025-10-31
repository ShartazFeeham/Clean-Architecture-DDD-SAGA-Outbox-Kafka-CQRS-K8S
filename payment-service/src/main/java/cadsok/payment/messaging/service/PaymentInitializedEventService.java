package cadsok.payment.messaging.service;

import cadsok.payment.domain.core.event.PaymentInitializedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentInitializedEventService extends PaymentEventProducerService<PaymentInitializedEvent> {

    @Value("${local.kafka-topic-names.payment-initialized}")
    private String topicName;

    public PaymentInitializedEventService(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
