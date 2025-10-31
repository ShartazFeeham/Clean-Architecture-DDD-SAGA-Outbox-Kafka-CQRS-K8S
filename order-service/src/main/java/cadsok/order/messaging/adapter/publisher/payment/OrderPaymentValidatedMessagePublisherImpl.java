package cadsok.order.messaging.adapter.publisher.payment;

import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderPaymentValidatedMessagePublisher;
import cadsok.order.domain.core.event.OrderCreatedEvent;
import cadsok.order.domain.core.event.OrderPaymentValidEvent;
import cadsok.order.messaging.service.OrderEventProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentValidatedMessagePublisherImpl extends OrderEventProducerService<OrderPaymentValidEvent>
        implements OrderPaymentValidatedMessagePublisher {
    public OrderPaymentValidatedMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
