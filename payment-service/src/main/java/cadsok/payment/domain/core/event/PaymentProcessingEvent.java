package cadsok.payment.domain.core.event;

import cadsok.payment.domain.core.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentProcessingEvent extends PaymentEvent {
    public PaymentProcessingEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt);
    }
}
