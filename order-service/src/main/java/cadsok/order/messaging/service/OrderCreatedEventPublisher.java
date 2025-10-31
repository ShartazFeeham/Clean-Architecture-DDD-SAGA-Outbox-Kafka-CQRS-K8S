package cadsok.order.messaging.service;

import cadsok.order.domain.core.event.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class OrderCreatedEventPublisher extends AbstractEventPublisher<OrderCreatedEvent> {

    @Value("${kafka.topic-names.order-events}")
    private String topicName;

    public OrderCreatedEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
