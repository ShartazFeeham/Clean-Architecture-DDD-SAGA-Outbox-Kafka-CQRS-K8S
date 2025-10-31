package cadsok.payment.messaging.adapter;

import cadsok.payment.domain.application.ports.output.event.PaymentCancelMessagePublisher;
import cadsok.payment.domain.core.event.PaymentCancelledEvent;
import cadsok.payment.messaging.service.PaymentCancelEventService;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentCancelMessagePublisherImpl extends PaymentCancelEventService implements PaymentCancelMessagePublisher {
    public PaymentCancelMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
