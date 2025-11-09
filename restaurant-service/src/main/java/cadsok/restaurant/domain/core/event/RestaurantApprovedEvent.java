package cadsok.restaurant.domain.core.event;


import cadsok.restaurant.data.entity.AcceptanceStatus;

import java.time.ZonedDateTime;

public class RestaurantApprovedEvent extends RestaurantEvent {
    public RestaurantApprovedEvent(String orderId, ZonedDateTime createdAt) {
        super(orderId, createdAt);
    }

    private final AcceptanceStatus status =  AcceptanceStatus.ACCEPTED;
    public AcceptanceStatus getStatus() {
        return status;
    }
}
