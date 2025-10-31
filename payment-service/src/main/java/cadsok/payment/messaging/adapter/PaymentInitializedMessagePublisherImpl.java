package cadsok.payment.messaging.adapter;

import cadsok.payment.domain.application.ports.output.event.PaymentInitializedMessagePublisher;
import cadsok.payment.domain.core.event.PaymentInitializedEvent;
import cadsok.payment.messaging.service.PaymentInitializedEventService;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentInitializedMessagePublisherImpl extends PaymentInitializedEventService implements PaymentInitializedMessagePublisher {
    public PaymentInitializedMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
