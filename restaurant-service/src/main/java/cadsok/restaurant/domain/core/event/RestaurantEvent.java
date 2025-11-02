package cadsok.restaurant.domain.core.event;

import commonmodule.domain.events.DomainEvent;

import java.time.ZonedDateTime;

public abstract class RestaurantEvent implements DomainEvent<String> {

    private final String orderId;
    private final ZonedDateTime createdAt;

    public RestaurantEvent(String orderId, ZonedDateTime createdAt) {
        this.orderId = orderId;
        this.createdAt = createdAt;
    }

    public String getOrderId() {
        return orderId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

}
