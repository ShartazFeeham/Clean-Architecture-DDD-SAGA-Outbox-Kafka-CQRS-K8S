package cadsok.order.messaging.outbox;

import cadsok.order.domain.core.event.OrderEvent;

public interface OutboxService {

    void handle(OrderEvent restaurantEvent, String topicName);

}
