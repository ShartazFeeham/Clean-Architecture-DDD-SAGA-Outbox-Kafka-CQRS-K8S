package cadsok.payment.messaging.service;

import cadsok.payment.domain.core.event.PaymentCompleteEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentCompleteEventService extends PaymentEventProducerService<PaymentCompleteEvent> {

    @Value("${local.kafka-topic-names.payment-complete}")
    private String topicName;

    public PaymentCompleteEventService(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
