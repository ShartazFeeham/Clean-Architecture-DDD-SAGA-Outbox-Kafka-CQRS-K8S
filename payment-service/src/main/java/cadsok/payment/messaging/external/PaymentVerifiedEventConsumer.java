package cadsok.payment.messaging.external;

import cadsok.payment.domain.application.models.PaymentInfoVarificationDto;
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
public class PaymentVerifiedEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentVerifiedEventConsumer.class);

    private final ObjectMapper objectMapper;
    private final PaymentVerificationMessageListener paymentVerificationMessageListener;

    @KafkaListener(topics = "${kafka.topic-names.payment-verified}", groupId = "${kafka.consumer.group.id}")
    @LogAction("Consuming payment verified event")
    public void onPaymentVerified(ConsumerRecord<String, String> record) {
        try {
            JsonNode root = objectMapper.readTree(record.value());
            boolean isValid = root.path("valid").asBoolean(false);
            String paymentIdStr = root.path("paymentId").asText(null);

            paymentVerificationMessageListener.handleVerificationResponse(new PaymentInfoVarificationDto(paymentIdStr, isValid));
        } catch (Exception e) {
            log.error("Error processing payment verified event", e);
            throw new RuntimeException(e);
        }
    }
}
