package cadsok.restaurant.domain.core.event;

import cadsok.restaurant.domain.core.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentCancelledEvent extends PaymentEvent{
    public PaymentCancelledEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt);
    }
}
