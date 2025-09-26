package cadsok.order.messaging.adapter.publisher.payment;

import cadsok.order.application.service.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import cadsok.order.domain.core.event.OrderCancelledEvent;
import cadsok.order.messaging.service.OrderEventProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelledPaymentRequestMessagePublisherImpl extends OrderEventProducerService<OrderCancelledEvent>
        implements OrderCancelledPaymentRequestMessagePublisher {
    public OrderCancelledPaymentRequestMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
