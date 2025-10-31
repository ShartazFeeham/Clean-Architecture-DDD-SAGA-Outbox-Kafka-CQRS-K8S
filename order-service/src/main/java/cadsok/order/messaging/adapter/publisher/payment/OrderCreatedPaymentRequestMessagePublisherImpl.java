package cadsok.order.messaging.adapter.publisher.payment;

import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import cadsok.order.domain.core.event.OrderCreatedEvent;
import cadsok.order.messaging.service.OrderCreatedEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedPaymentRequestMessagePublisherImpl extends OrderCreatedEventPublisher<OrderCreatedEvent>
        implements OrderCreatedPaymentRequestMessagePublisher {
    public OrderCreatedPaymentRequestMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
