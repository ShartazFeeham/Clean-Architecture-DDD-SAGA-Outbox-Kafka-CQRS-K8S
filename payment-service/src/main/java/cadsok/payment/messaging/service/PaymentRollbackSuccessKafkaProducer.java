package cadsok.payment.messaging.service;

import cadsok.payment.domain.core.event.PaymentRollbackFailedEvent;
import cadsok.payment.domain.core.event.PaymentRollbackSucceedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentRollbackSuccessKafkaProducer extends PaymentEventProducer<PaymentRollbackSucceedEvent> {

    @Value("${kafka.topic-names.payment-rollback-success}")
    private String topicName;

    public PaymentRollbackSuccessKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
