package cadsok.payment.messaging.adapter.output;

import cadsok.payment.domain.application.ports.output.event.PaymentCompleteMessagePublisher;
import cadsok.payment.messaging.service.PaymentCompleteKafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentCompleteMessagePublisherImpl extends PaymentCompleteKafkaProducer
        implements PaymentCompleteMessagePublisher {
    public PaymentCompleteMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
