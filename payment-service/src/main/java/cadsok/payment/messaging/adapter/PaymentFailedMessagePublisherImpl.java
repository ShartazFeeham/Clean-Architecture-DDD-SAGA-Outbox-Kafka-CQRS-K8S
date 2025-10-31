package cadsok.payment.messaging.adapter;

import cadsok.payment.domain.application.ports.output.event.PaymentIFailedMessagePublisher;
import cadsok.payment.messaging.service.PaymentFailedEventService;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentFailedMessagePublisherImpl extends PaymentFailedEventService implements PaymentIFailedMessagePublisher {
    public PaymentFailedMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
