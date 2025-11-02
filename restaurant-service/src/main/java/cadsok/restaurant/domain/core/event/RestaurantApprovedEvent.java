package cadsok.restaurant.domain.core.event;


import java.time.ZonedDateTime;

public class RestaurantApprovedEvent extends RestaurantEvent {
    public RestaurantApprovedEvent(String orderId, ZonedDateTime createdAt) {
        super(orderId, createdAt);
    }
}
