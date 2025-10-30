package cadsok.payment.domain.core.event;

import cadsok.payment.domain.core.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentInfoInitializedEvent extends PaymentEvent {
    public PaymentInfoInitializedEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt);
    }
}
