package cadsok.order.messaging.external;

import cadsok.order.domain.application.ports.input.message.listener.payment.PaymentResponseMessageListener;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentCompletedEventConsumer {

    private final Logger log = LoggerFactory.getLogger(PaymentCompletedEventConsumer.class);
    private final ObjectMapper objectMapper;
    private final PaymentResponseMessageListener paymentResponseMessageListener;

    public PaymentCompletedEventConsumer(ObjectMapper objectMapper,
                                         PaymentResponseMessageListener paymentResponseMessageListener) {
        this.objectMapper = objectMapper;
        this.paymentResponseMessageListener = paymentResponseMessageListener;
    }

    @KafkaListener(topics = "${kafka.topic-names.payment-complete}", groupId = "${kafka.consumer.group.id}")
    public void consumeMessage(ConsumerRecord<String, String> record) {
        log.info("Consumed message: Key: {}, Topic: {}, Partition: {}, Offset: {}",
                record.key(), record.topic(), record.partition(), record.offset());
        try {
            JsonNode root = objectMapper.readTree(record.value());
            JsonNode paymentNode = root.path("payment");
            String orderIdStr = paymentNode.path("orderId").path("value").asText(null);

            paymentResponseMessageListener.paymentCompleted(orderIdStr);
        } catch (Exception e) {
            log.error("Error processing payment validation message; will be retried or sent to DLQ", e);
            throw new RuntimeException(e);
        }
    }
}
