package cadsok.order.messaging.service;

import cadsok.order.domain.core.event.OrderPaidEvent;
import cadsok.order.messaging.service.base.AbstractEventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class OrderPaidEventPublisher extends AbstractEventPublisher<OrderPaidEvent> {

    @Value("${kafka.topic-names.order-paid}")
    private String topicName;

    public OrderPaidEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
