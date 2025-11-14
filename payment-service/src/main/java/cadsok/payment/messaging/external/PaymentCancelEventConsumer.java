package cadsok.payment.messaging.external;

import cadsok.payment.domain.application.ports.input.event.PaymentRollbackEventListener;
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
public class PaymentCancelEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentCancelEventConsumer.class);

    private final ObjectMapper objectMapper;
    private final PaymentRollbackEventListener paymentRollbackEventListener;

    @KafkaListener(topics = "${kafka.topic-names.payment-cancel-con}", groupId = "${kafka.consumer.group.id}")
    @LogAction("Consuming payment cancel event for rolling back")
    public void onPaymentVerified(ConsumerRecord<String, String> record) {
        try {
            JsonNode root = objectMapper.readTree(record.value()).path("payload");
            String paymentId = root.path("payment").path("paymentId").path("value").asText();

            paymentRollbackEventListener.rollbackPayment(paymentId);
        } catch (Exception e) {
            log.error("Error processing payment verified event", e);
            throw new RuntimeException(e);
        }
    }
}
