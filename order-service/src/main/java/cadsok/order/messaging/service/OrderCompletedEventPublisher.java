package cadsok.order.messaging.service;

import cadsok.order.domain.core.event.OrderCancelledEvent;
import cadsok.order.domain.core.event.OrderCompletedEvent;
import cadsok.order.messaging.service.base.AbstractEventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class OrderCompletedEventPublisher extends AbstractEventPublisher<OrderCompletedEvent> {

    @Value("${kafka.topic-names.order-completed}")
    private String topicName;

    public OrderCompletedEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
