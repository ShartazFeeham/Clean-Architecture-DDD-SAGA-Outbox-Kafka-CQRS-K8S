package cadsok.payment.messaging.adapter.output;

import cadsok.payment.domain.application.ports.output.event.PaymentInitializedMessagePublisher;
import cadsok.payment.messaging.service.PaymentInitializedKafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;

public class PaymentInitializedMessagePublisherImpl extends PaymentInitializedKafkaProducer implements PaymentInitializedMessagePublisher {
    public PaymentInitializedMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
