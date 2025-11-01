package cadsok.order.messaging.adapter.publisher.payment;

import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCancelledMessagePublisher;
import cadsok.order.messaging.service.OrderCancelledEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelledMessagePublisherImpl extends OrderCancelledEventPublisher
        implements OrderCancelledMessagePublisher {
    public OrderCancelledMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}