package cadsok.payment.messaging.service;

import cadsok.payment.domain.core.event.PaymentRollbackFailedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentRollbackFailedKafkaProducer extends PaymentEventProducer<PaymentRollbackFailedEvent> {

    @Value("${kafka.topic-names.payment-rollback-failed}")
    private String topicName;

    public PaymentRollbackFailedKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
