package cadsok.restaurant.messaging.outbox;

import cadsok.restaurant.data.entity.RestaurantOutbox;
import cadsok.restaurant.domain.core.event.RestaurantEvent;

public interface OutboxService {

    void handle(RestaurantOutbox restaurantOutbox, RestaurantEvent restaurantEvent, String topicName);

}
