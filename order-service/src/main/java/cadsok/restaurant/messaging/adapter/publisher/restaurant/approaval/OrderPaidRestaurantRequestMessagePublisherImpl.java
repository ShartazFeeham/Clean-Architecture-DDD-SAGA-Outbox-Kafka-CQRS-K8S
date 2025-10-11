package cadsok.restaurant.messaging.adapter.publisher.restaurant.approaval;

import cadsok.restaurant.domain.application.ports.output.message.publisher.restaurant.approval.OrderPaidRestaurantRequestMessagePublisher;
import cadsok.restaurant.domain.core.event.OrderPaidEvent;
import cadsok.restaurant.messaging.service.OrderEventProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderPaidRestaurantRequestMessagePublisherImpl extends OrderEventProducerService<OrderPaidEvent>
        implements OrderPaidRestaurantRequestMessagePublisher {
    public OrderPaidRestaurantRequestMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
