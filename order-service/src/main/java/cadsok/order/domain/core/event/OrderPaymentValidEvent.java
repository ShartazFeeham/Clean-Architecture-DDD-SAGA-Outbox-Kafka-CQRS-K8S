package cadsok.order.domain.core.event;

import cadsok.order.domain.core.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaymentValidEvent extends OrderEvent {
    public OrderPaymentValidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}

