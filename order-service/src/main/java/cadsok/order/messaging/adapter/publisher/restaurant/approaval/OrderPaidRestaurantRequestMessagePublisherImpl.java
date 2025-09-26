package cadsok.order.messaging.adapter.publisher.restaurant.approaval;

import cadsok.order.application.service.ports.output.message.publisher.restaurant.approval.OrderPaidRestaurantRequestMessagePublisher;
import cadsok.order.domain.core.event.OrderPaidEvent;
import cadsok.order.messaging.service.OrderEventProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderPaidRestaurantRequestMessagePublisherImpl extends OrderEventProducerService<OrderPaidEvent>
        implements OrderPaidRestaurantRequestMessagePublisher {
    public OrderPaidRestaurantRequestMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
