package cadsok.restaurant.domain.core.event;


import cadsok.restaurant.data.entity.AcceptanceStatus;

import java.time.ZonedDateTime;

public class RestaurantRejectedEvent extends RestaurantEvent {
    public RestaurantRejectedEvent(String orderId, ZonedDateTime createdAt) {
        super(orderId, createdAt);
    }

    private final AcceptanceStatus status =  AcceptanceStatus.REJECTED;
    public AcceptanceStatus getStatus() {
        return status;
    }
}
