package cadsok.payment.messaging.adapter;

import cadsok.payment.domain.application.ports.output.event.PaymentProcessingMessagePublisher;
import cadsok.payment.domain.core.event.PaymentInitializedEvent;
import cadsok.payment.messaging.service.PaymentProcessingEventService;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentProcessingMessagePublisherImpl extends PaymentProcessingEventService implements PaymentProcessingMessagePublisher {
    public PaymentProcessingMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
