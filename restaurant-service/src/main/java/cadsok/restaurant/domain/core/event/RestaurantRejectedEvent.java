package cadsok.restaurant.domain.core.event;


import java.time.ZonedDateTime;

public class RestaurantRejectedEvent extends RestaurantEvent {
    public RestaurantRejectedEvent(String orderId, ZonedDateTime createdAt) {
        super(orderId, createdAt);
    }
}
