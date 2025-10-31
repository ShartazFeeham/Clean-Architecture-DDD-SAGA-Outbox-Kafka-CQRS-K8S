package cadsok.payment.messaging.adapter.output;

import cadsok.payment.domain.application.ports.output.event.PaymentIFailedMessagePublisher;
import cadsok.payment.messaging.service.PaymentFailedKafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentFailedMessagePublisherImpl extends PaymentFailedKafkaProducer implements PaymentIFailedMessagePublisher {
    public PaymentFailedMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
