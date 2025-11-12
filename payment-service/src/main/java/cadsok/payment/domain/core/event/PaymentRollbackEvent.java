package cadsok.payment.domain.core.event;

import cadsok.payment.domain.core.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentRollbackEvent extends PaymentEvent {
    public PaymentRollbackEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt);
    }
}
