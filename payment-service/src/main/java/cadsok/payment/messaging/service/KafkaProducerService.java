package cadsok.payment.messaging.service;

import cadsok.payment.messaging.exception.PaymentEventException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import commonmodule.domain.events.DomainEvent;
import commonmodule.infra.logging.AsyncLoggingHelper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
abstract class KafkaProducerService<T extends DomainEvent<?>> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    protected final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    abstract protected String getTopicName();

    abstract public void publish(T event);

    protected void send(ProducerRecord<String, String> record) {
        String trackingHash = AsyncLoggingHelper.captureTrackingHash(); // Capture tracking hash before async operation
        kafkaTemplate.send(record).whenCompleteAsync(logTheResult(record, trackingHash));
    }

    private BiConsumer<SendResult<String, String>, Throwable> logTheResult(ProducerRecord<String, String> record, String trackingHash) {
        return (result, exception) -> {
            AsyncLoggingHelper.restoreTrackingHash(trackingHash);
            String successMessage = null, failureMessage = null;

            if (exception == null) {
                successMessage = String.format("successful [topic: %s, partition: %d, offset: %d]",
                        record.topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            } else {
                failureMessage = String.format("failed [topic: %s, key: %s]",
                        record.topic(),
                        record.key());
            }

            AsyncLoggingHelper.logAsync(this.getClass(), "Messaging", "Message publish", successMessage, failureMessage, exception);
        };
    }

    protected String getDataAsString(T data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new PaymentEventException("Error while serializing OrderEvent to JSON", e);
        }
    }
}
