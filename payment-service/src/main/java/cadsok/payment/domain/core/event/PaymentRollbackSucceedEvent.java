package cadsok.payment.domain.core.event;

import cadsok.payment.domain.core.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentRollbackSucceedEvent extends PaymentRollbackEvent {
    public PaymentRollbackSucceedEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt);
    }
}
