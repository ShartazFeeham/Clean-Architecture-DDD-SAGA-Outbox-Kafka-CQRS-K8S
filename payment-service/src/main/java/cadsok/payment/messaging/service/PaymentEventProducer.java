package cadsok.payment.messaging.service;

import cadsok.payment.domain.core.event.PaymentEvent;
import commonmodule.infra.logging.LogAction;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import java.util.UUID;

public abstract class PaymentEventProducer<T extends PaymentEvent> extends KafkaProducerService<T> {

    public PaymentEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    @LogAction("Publishing order event to Kafka")
    public void publish(T event) {
        // Setting a totally random key for better distribution across partitions as we don't need to place this
        // event in order with other events of the same order in same partition
        String key = UUID.randomUUID().toString();
        String data = super.getDataAsString(event);
        ProducerRecord<String, String> record = new ProducerRecord<>(getTopicName(), key, data);
        super.send(record);
    }
}
