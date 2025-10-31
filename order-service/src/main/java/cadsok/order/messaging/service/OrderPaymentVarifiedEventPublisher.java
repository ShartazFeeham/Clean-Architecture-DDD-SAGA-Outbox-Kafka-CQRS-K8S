package cadsok.order.messaging.service;

import cadsok.order.domain.core.event.OrderPaymentValidEvent;
import cadsok.order.messaging.service.base.AbstractEventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class OrderPaymentVarifiedEventPublisher extends AbstractEventPublisher<OrderPaymentValidEvent> {

    @Value("${kafka.topic-names.payment-verified}")
    private String topicName;

    public OrderPaymentVarifiedEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }
}
