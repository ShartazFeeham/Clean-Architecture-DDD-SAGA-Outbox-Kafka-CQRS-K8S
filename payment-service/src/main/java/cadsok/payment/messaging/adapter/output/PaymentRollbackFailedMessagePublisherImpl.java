package cadsok.payment.messaging.adapter.output;

import cadsok.payment.domain.application.ports.output.event.PaymentRollbackFailedMessagePublisher;
import cadsok.payment.domain.application.ports.output.event.PaymentRollbackSuccessMessagePublisher;
import cadsok.payment.messaging.service.PaymentRollbackFailedKafkaProducer;
import cadsok.payment.messaging.service.PaymentRollbackSuccessKafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentRollbackFailedMessagePublisherImpl extends PaymentRollbackFailedKafkaProducer
        implements PaymentRollbackFailedMessagePublisher {
    public PaymentRollbackFailedMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
