package cadsok.payment.messaging.external;

import cadsok.payment.domain.application.models.PaymentGatewayRequestDto;
import cadsok.payment.domain.application.ports.input.event.PaymentGatewayMessageListener;
import cadsok.payment.domain.application.ports.output.repository.PaymentRepository;
import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.values.PaymentId;
import cadsok.payment.gateway.PaymentGatewaySimulator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonmodule.domain.values.PaymentStatus;
import commonmodule.infra.logging.LogAction;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentProcessingExecutorConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentProcessingExecutorConsumer.class);

    private final PaymentRepository paymentRepository;
    private final PaymentGatewaySimulator gateway;
    private final ObjectMapper objectMapper;
    private final PaymentGatewayMessageListener paymentGatewayMessageListener;

    @KafkaListener(topics = "${kafka.topic-names.payment-processing}", groupId = "${kafka.consumer.group.id}")
    @LogAction("Consuming payment processing event and executing payment")
    public void onPaymentProcessing(ConsumerRecord<String, String> record) {
        try {
            JsonNode root = objectMapper.readTree(record.value());
            // Expect our own event shape: { "payment": { "paymentId": {"value": "..."}, ... } }
            String paymentIdStr = root.path("payment").path("paymentId").path("value").asText(null);
            if (paymentIdStr == null) {
                log.warn("paymentId not found in processing event payload; skipping. payload={}", record.value());
                return;
            }
            Payment payment = paymentRepository.getPaymentById(new PaymentId(UUID.fromString(paymentIdStr))).orElse(null);
            if (payment == null) {
                log.warn("Payment not found for id={}, skipping", paymentIdStr);
                return;
            }

            boolean success = gateway.succeed();
            PaymentStatus paymentStatus = success ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;
            paymentGatewayMessageListener.handlePaymentGatewayResponse(new PaymentGatewayRequestDto(paymentIdStr, paymentStatus));
        } catch (Exception e) {
            log.error("Error processing payment execution", e);
            throw new RuntimeException(e);
        }
    }
}
