package cadsok.restaurant.messaging.service;

import cadsok.restaurant.domain.core.event.RestaurantEvent;
import commonmodule.infra.logging.LogAction;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RestaurantEventProducer extends KafkaProducerService<RestaurantEvent> {

    @Value("${kafka.topic-names.restaurant-event}")
    private String topicName;

    public RestaurantEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopicName() {
        return topicName;
    }

    @Override
    @LogAction("Publishing restaurant event to Kafka")
    public void publish(RestaurantEvent event) {
        // Setting a totally random key for better distribution across partitions as we don't need to place this
        // event in order with other events of the same order in same partition
        String key = UUID.randomUUID().toString();
        String data = super.getDataAsString(event);
        ProducerRecord<String, String> record = new ProducerRecord<>(getTopicName(), key, data);
        super.send(record);
    }
}
