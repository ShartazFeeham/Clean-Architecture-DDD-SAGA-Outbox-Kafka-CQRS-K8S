package cadsok.payment.messaging.external;

import cadsok.payment.domain.application.models.PaymentCancelRequestDto;
import cadsok.payment.domain.application.models.PaymentInfoVarificationDto;
import cadsok.payment.domain.application.ports.input.event.PaymentCancelEventListener;
import cadsok.payment.domain.application.ports.input.event.PaymentVerificationMessageListener;
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
public class OrderCancelledEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderCancelledEventConsumer.class);

    private final ObjectMapper objectMapper;
    private final PaymentCancelEventListener paymentCancelEventListener;

    @KafkaListener(topics = "${kafka.topic-names.order-cancelled-con}", groupId = "${kafka.consumer.group.id}")
    @LogAction("Consuming payment verified event")
    public void onPaymentVerified(ConsumerRecord<String, String> record) {
        try {
            JsonNode root = objectMapper.readTree(record.value()).path("payload");
            String trackingId = root.path("order").path("trackingId").path("value").asText();

            paymentCancelEventListener.handlePaymentCancelEvent(new PaymentCancelRequestDto(trackingId));
        } catch (Exception e) {
            log.error("Error processing payment verified event", e);
            throw new RuntimeException(e);
        }
    }
}
