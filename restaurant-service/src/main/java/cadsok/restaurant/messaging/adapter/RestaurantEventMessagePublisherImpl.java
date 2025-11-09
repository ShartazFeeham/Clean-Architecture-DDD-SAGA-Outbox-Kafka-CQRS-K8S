package cadsok.restaurant.messaging.adapter;

import cadsok.restaurant.domain.application.ports.output.event.RestaurantEventPublisher;
import cadsok.restaurant.messaging.service.RestaurantEventProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEventMessagePublisherImpl extends RestaurantEventProducer implements RestaurantEventPublisher {
    public RestaurantEventMessagePublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }
}