package cadsok.restaurant.messaging.adapter.publisher.payment;

import cadsok.restaurant.domain.application.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import cadsok.restaurant.domain.core.event.OrderCreatedEvent;
import cadsok.restaurant.messaging.service.OrderEventProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedPaymentRequestMessagePublisherImpl extends OrderEventProducerService<OrderCreatedEvent>
        implements OrderCreatedPaymentRequestMessagePublisher {
    public OrderCreatedPaymentRequestMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
