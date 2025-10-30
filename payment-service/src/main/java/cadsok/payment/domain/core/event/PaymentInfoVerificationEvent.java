package cadsok.payment.domain.core.event;

import cadsok.payment.domain.core.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentInfoVerificationEvent extends PaymentEvent {
    public PaymentInfoVerificationEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt);
    }
}
