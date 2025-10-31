package cadsok.payment.messaging.adapter.output;

import cadsok.payment.domain.application.ports.output.event.PaymentCancelMessagePublisher;
import cadsok.payment.messaging.service.PaymentCancelKafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentCancelMessagePublisherImpl extends PaymentCancelKafkaProducer
        implements PaymentCancelMessagePublisher {
    public PaymentCancelMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
