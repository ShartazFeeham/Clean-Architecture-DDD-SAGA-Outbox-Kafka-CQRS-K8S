package cadsok.payment.messaging.adapter.output;

import cadsok.payment.domain.application.ports.output.event.PaymentProcessingMessagePublisher;
import cadsok.payment.messaging.service.PaymentProcessingKafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentProcessingMessagePublisherImpl extends PaymentProcessingKafkaProducer implements PaymentProcessingMessagePublisher {
    public PaymentProcessingMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
