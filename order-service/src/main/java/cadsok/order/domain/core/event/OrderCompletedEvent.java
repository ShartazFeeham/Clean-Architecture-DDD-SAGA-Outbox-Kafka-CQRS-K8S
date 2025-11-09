package cadsok.order.domain.core.event;

import cadsok.order.domain.core.entity.Order;

import java.time.ZonedDateTime;

public class OrderCompletedEvent extends OrderEvent {
    public OrderCompletedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
