package cadsok.payment.messaging.outbox;

import cadsok.payment.data.entity.PaymentOutbox;
import cadsok.payment.data.repository.PaymentOutboxRepository;
import cadsok.payment.domain.core.event.PaymentEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxServiceImpl implements OutboxService {

    private final ObjectMapper objectMapper;
    private final PaymentOutboxRepository paymentOutboxRepository;

    @Override
    @Transactional
    public void handle(PaymentEvent paymentEvent, String topicName) {
        try {
            String payload = objectMapper.writeValueAsString(paymentEvent);
            String eventType = paymentEvent.getClass().getSimpleName();
            var outboxEntity = PaymentOutbox.create(payload, topicName, paymentEvent.getPayment().getOrderId().toString(), eventType);
            paymentOutboxRepository.save(outboxEntity);
            paymentOutboxRepository.delete(outboxEntity);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize domain event for outbox: {}", e.getMessage());
            throw new RuntimeException("Failed to persist domain event to outbox. Rolling back transaction.", e);
        }
    }
}
