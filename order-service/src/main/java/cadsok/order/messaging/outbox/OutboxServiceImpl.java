package cadsok.order.messaging.outbox;

import cadsok.order.data.entities.OrderOutbox;
import cadsok.order.data.repositories.OrderOutboxRepository;
import cadsok.order.domain.core.event.OrderEvent;
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
    private final OrderOutboxRepository orderOutboxRepository;

    @Override
    @Transactional
    public void handle(OrderEvent orderEvent, String topicName) {
        try {
            String payload = objectMapper.writeValueAsString(orderEvent);
            String eventType = orderEvent.getClass().getSimpleName();
            var outboxEntity = OrderOutbox.create(payload, topicName, orderEvent.getOrder().getTrackingId().toString(), eventType);
            orderOutboxRepository.save(outboxEntity);
            orderOutboxRepository.delete(outboxEntity);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize domain event for outbox: {}", e.getMessage());
            throw new RuntimeException("Failed to persist domain event to outbox. Rolling back transaction.", e);
        }
    }
}
