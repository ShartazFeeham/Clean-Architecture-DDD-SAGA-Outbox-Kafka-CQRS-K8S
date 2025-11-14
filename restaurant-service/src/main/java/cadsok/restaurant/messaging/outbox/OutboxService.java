package cadsok.restaurant.messaging.outbox;

import cadsok.restaurant.domain.core.event.RestaurantEvent;

public interface OutboxService {

    void handle(RestaurantEvent restaurantEvent, String topicName);

}
