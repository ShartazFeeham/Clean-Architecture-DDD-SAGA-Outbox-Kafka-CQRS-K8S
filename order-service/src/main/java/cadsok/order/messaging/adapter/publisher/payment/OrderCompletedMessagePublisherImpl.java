package cadsok.order.messaging.adapter.publisher.payment;

import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCompletedMessagePublisher;
import cadsok.order.messaging.service.OrderCompletedEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCompletedMessagePublisherImpl extends OrderCompletedEventPublisher
        implements OrderCompletedMessagePublisher {
    public OrderCompletedMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}