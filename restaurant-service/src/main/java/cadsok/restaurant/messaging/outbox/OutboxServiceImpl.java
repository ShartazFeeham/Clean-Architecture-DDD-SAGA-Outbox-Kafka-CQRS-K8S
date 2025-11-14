package cadsok.restaurant.messaging.outbox;

import cadsok.restaurant.data.entity.RestaurantOutbox;
import cadsok.restaurant.data.repository.RestaurantOutboxRepository;
import cadsok.restaurant.domain.core.event.RestaurantEvent;
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
    private final RestaurantOutboxRepository restaurantOutboxRepository;

    @Override
    @Transactional
    public void handle(RestaurantEvent restaurantEvent, String topicName) {
        try {
            String payload = objectMapper.writeValueAsString(restaurantEvent);
            String eventType = restaurantEvent.getClass().getSimpleName();
            var outboxEntity = RestaurantOutbox.create(payload, topicName, restaurantEvent.getOrderId(), eventType);
            restaurantOutboxRepository.save(outboxEntity);
            restaurantOutboxRepository.delete(outboxEntity);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize domain event for outbox: {}", e.getMessage());
            throw new RuntimeException("Failed to persist domain event to outbox. Rolling back transaction.", e);
        }
    }
}
