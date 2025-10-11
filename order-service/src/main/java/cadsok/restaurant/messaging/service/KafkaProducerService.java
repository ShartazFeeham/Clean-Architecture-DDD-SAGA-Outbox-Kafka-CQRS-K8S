package cadsok.restaurant.messaging.service;

import cadsok.restaurant.messaging.exception.OrderEventException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import commonmodule.domain.events.DomainEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
abstract class KafkaProducerService<T extends DomainEvent<?>> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);
    protected final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    abstract protected String getTopicName(String topicName);

    abstract public void publish(T event);

    protected void send(ProducerRecord<String, String> record) {
        log.info("Attempting to publish message to Kafka broker. Key: {}, Value: {}, Topic: {}, Partition: {}, Headers: {}, Timestamp: {}",
                record.key(), record.value(), record.topic(), record.partition(), record.headers(), record.timestamp());

        kafkaTemplate.send(record)
                .whenCompleteAsync((resultMetadata, exception) -> {
                    if (exception != null) {
                        log.error("KAFKA RESPONSE - Failed to send message!", exception);
                    } else {
                        log.info("KAFKA RESPONSE - Message sent successfully. Key: {}, Value: {}, topic: {}, Partition: {}, Headers: {}, " +
                                        "Timestamp: {}, Offset: {}", resultMetadata.getProducerRecord().key(),
                                resultMetadata.getProducerRecord().value(), resultMetadata.getProducerRecord().topic(),
                                resultMetadata.getProducerRecord().partition(), resultMetadata.getProducerRecord().headers(),
                                resultMetadata.getProducerRecord().timestamp(), resultMetadata.getRecordMetadata().offset());
                    }
                });
    }

    protected String getDataAsString(T data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new OrderEventException("Error while serializing OrderEvent to JSON", e);
        }
    }
}