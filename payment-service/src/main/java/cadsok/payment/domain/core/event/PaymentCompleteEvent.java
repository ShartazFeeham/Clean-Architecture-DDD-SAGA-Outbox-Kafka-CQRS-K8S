package cadsok.payment.domain.core.event;

import cadsok.payment.domain.core.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentCompleteEvent extends PaymentEvent {
    public PaymentCompleteEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt);
    }
}
