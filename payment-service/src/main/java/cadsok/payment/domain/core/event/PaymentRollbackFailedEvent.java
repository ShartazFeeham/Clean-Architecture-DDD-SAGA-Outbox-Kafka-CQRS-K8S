package cadsok.payment.domain.core.event;

import cadsok.payment.domain.core.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentRollbackFailedEvent extends PaymentRollbackEvent {
    public PaymentRollbackFailedEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt);
    }
}
