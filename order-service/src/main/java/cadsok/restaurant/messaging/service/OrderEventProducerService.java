package cadsok.restaurant.messaging.service;

import cadsok.restaurant.domain.core.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

@Slf4j
public class OrderEventProducerService<T extends OrderEvent> extends KafkaProducerService<T> {

    @Value("${local.kafka-topic-name}")
    private String topicName;

    public OrderEventProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void publish(T event) {
        log.info("Publishing order event: {}", event);
        // Setting a totally random key for better distribution across partitions as we don't need to place this
        // event in order with other events of the same order in same partition
        String key = UUID.randomUUID().toString();
        String data = super.getDataAsString(event);
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, data);
        super.send(record);
    }

    @Override
    protected String getTopicName(String topicName) {
        return topicName;
    }
}
