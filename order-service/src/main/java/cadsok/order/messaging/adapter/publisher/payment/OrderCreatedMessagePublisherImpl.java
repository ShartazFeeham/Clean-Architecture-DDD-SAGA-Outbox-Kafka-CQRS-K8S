package cadsok.order.messaging.adapter.publisher.payment;

import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCreatedMessagePublisher;
import cadsok.order.messaging.service.OrderCreatedEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedMessagePublisherImpl extends OrderCreatedEventPublisher
        implements OrderCreatedMessagePublisher {
    public OrderCreatedMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
