package cadsok.restaurant.messaging.adapter.publisher.payment;

import cadsok.restaurant.domain.application.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import cadsok.restaurant.domain.core.event.OrderCancelledEvent;
import cadsok.restaurant.messaging.service.OrderEventProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelledPaymentRequestMessagePublisherImpl extends OrderEventProducerService<OrderCancelledEvent>
        implements OrderCancelledPaymentRequestMessagePublisher {
    public OrderCancelledPaymentRequestMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
