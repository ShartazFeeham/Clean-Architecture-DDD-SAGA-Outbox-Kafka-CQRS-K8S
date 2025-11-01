package cadsok.order.messaging.adapter.publisher.payment;

import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderPaidMessagePublisher;
import cadsok.order.messaging.service.OrderPaidEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderPaidMessagePublisherImpl extends OrderPaidEventPublisher implements OrderPaidMessagePublisher {
    public OrderPaidMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}