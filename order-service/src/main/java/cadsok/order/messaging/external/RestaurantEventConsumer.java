package cadsok.order.messaging.external;

import cadsok.order.domain.application.ports.input.message.listener.restaurant.RestaurantMessageListener;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RestaurantEventConsumer {

    private final Logger log = LoggerFactory.getLogger(RestaurantEventConsumer.class);
    private final ObjectMapper objectMapper;
    private final RestaurantMessageListener restaurantMessageListener;

    public RestaurantEventConsumer(ObjectMapper objectMapper, RestaurantMessageListener restaurantMessageListener) {
        this.objectMapper = objectMapper;
        this.restaurantMessageListener = restaurantMessageListener;
    }

    @KafkaListener(topics = "${kafka.topic-names.restaurant-event-con}", groupId = "${kafka.consumer.group.id}")
    public void consumeMessage(ConsumerRecord<String, String> record) {
        log.info("Consumed message: Key: {}, Topic: {}, Partition: {}, Offset: {}",
                record.key(), record.topic(), record.partition(), record.offset());
        try {
            JsonNode root = objectMapper.readTree(record.value()).path("payload");
            String orderId = root.path("orderId").asText();
            String status = root.path("status").asText();

            boolean approved = status != null && status.equals("ACCEPTED");
            restaurantMessageListener.orderApproved(orderId, approved, "Restaurant cancelled order");

        } catch (Exception e) {
            log.error("Error processing payment validation message; will be retried or sent to DLQ", e);
            throw new RuntimeException(e);
        }
    }
}
