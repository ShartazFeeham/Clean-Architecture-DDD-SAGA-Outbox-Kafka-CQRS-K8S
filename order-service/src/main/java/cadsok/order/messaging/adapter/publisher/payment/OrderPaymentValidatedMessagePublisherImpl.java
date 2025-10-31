package cadsok.order.messaging.adapter.publisher.payment;

import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderPaymentValidatedMessagePublisher;
import cadsok.order.messaging.service.OrderPaymentVarifiedEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentValidatedMessagePublisherImpl extends OrderPaymentVarifiedEventPublisher
        implements OrderPaymentValidatedMessagePublisher {
    public OrderPaymentValidatedMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
