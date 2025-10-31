package cadsok.order.messaging.service;

import cadsok.order.domain.core.event.OrderCancelledEvent;
import cadsok.order.messaging.service.base.AbstractEventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class OrderCancelledEventPublisher extends AbstractEventPublisher<OrderCancelledEvent> {

    @Value("${kafka.topic-names.order-cancelled}")
    private String topicName;

    public OrderCancelledEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
