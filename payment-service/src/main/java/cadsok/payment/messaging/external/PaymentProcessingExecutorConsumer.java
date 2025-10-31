package cadsok.payment.messaging.external;

import cadsok.payment.domain.application.ports.output.event.PaymentCompleteMessagePublisher;
import cadsok.payment.domain.application.ports.output.event.PaymentIFailedMessagePublisher;
import cadsok.payment.domain.application.ports.output.repository.PaymentRepository;
import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.PaymentCompleteEvent;
import cadsok.payment.domain.core.event.PaymentFailedEvent;
import cadsok.payment.domain.core.services.PaymentDomainService;
import cadsok.payment.gateway.PaymentGatewaySimulator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonmodule.infra.logging.LogAction;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentProcessingExecutorConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentProcessingExecutorConsumer.class);

    private final PaymentRepository paymentRepository;
    private final PaymentDomainService paymentDomainService;
    private final PaymentCompleteMessagePublisher paymentCompleteMessagePublisher;
    private final PaymentIFailedMessagePublisher paymentFailedMessagePublisher;
    private final PaymentGatewaySimulator gateway;
    private final ObjectMapper objectMapper;

    public PaymentProcessingExecutorConsumer(PaymentRepository paymentRepository,
                                             PaymentDomainService paymentDomainService,
                                             PaymentCompleteMessagePublisher paymentCompleteMessagePublisher,
                                             PaymentIFailedMessagePublisher paymentFailedMessagePublisher,
                                             PaymentGatewaySimulator gateway,
                                             ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentDomainService = paymentDomainService;
        this.paymentCompleteMessagePublisher = paymentCompleteMessagePublisher;
        this.paymentFailedMessagePublisher = paymentFailedMessagePublisher;
        this.gateway = gateway;
        this.objectMapper = objectMapper;
    }

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
            Payment payment = paymentRepository.getPaymentById(new cadsok.payment.domain.core.values.PaymentId(UUID.fromString(paymentIdStr)))
                    .orElse(null);
            if (payment == null) {
                log.warn("Payment not found for id={}, skipping", paymentIdStr);
                return;
            }

            boolean success = gateway.succeed();
            if (success) {
                PaymentCompleteEvent completed = paymentDomainService.completePayment(payment);
                paymentRepository.savePayment(payment);
                paymentCompleteMessagePublisher.publish(completed);
            } else {
                PaymentFailedEvent failed = paymentDomainService.failedPayment(payment);
                paymentRepository.savePayment(payment);
                paymentFailedMessagePublisher.publish(failed);
            }
        } catch (Exception e) {
            log.error("Error processing payment execution", e);
            throw new RuntimeException(e);
        }
    }
}
