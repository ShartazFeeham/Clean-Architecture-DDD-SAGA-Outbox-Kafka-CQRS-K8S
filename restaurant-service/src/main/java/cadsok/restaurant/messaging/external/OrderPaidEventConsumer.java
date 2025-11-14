package cadsok.restaurant.messaging.external;

import cadsok.restaurant.domain.application.ports.input.event.OrderPaidEventListener;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonmodule.infra.logging.LogAction;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderPaidEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderPaidEventConsumer.class);

    private final ObjectMapper objectMapper;
    private final OrderPaidEventListener orderPaidEventListener;

    @KafkaListener(topics = "${kafka.topic-names.order-paid-con}", groupId = "${kafka.consumer.group.id}")
    @LogAction("Consuming order paid event and saving data in database")
    public void onPaymentProcessing(ConsumerRecord<String, String> record) {
        try {
            JsonNode root = objectMapper.readTree(record.value()).path("payload");
            String orderId = root.path("order").path("trackingId").path("value").asText();
            String detailJson = new ObjectMapper().writeValueAsString(root.path("order"));

            orderPaidEventListener.handleOrderPaidEvent(orderId, detailJson);
        } catch (Exception e) {
            log.error("Error processing payment execution", e);
            throw new RuntimeException(e);
        }
    }
}
