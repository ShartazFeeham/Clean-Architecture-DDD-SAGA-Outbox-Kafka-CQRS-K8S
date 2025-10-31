package cadsok.payment.messaging.adapter;

import cadsok.payment.domain.application.ports.output.event.PaymentCompleteMessagePublisher;
import cadsok.payment.domain.core.event.PaymentCompleteEvent;
import cadsok.payment.messaging.service.PaymentCompleteEventService;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentCompleteMessagePublisherImpl extends PaymentCompleteEventService implements PaymentCompleteMessagePublisher {
    public PaymentCompleteMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
