package cadsok.payment.domain.core.event;

import cadsok.payment.domain.core.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentFailedEvent extends PaymentEvent {
    public PaymentFailedEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt);
    }
}
