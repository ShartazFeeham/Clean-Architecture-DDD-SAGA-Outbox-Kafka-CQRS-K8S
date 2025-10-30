package cadsok.payment.domain.core.event;

import cadsok.payment.domain.core.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentInitializedEvent extends PaymentEvent {
    public PaymentInitializedEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt);
    }
}
