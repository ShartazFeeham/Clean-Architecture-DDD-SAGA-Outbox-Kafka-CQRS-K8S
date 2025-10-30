package cadsok.payment.domain.core.event;

import cadsok.payment.domain.core.entity.Payment;
import commonmodule.domain.events.DomainEvent;

import java.time.ZonedDateTime;

public abstract class PaymentEvent implements DomainEvent<Payment> {

    private final Payment payment;
    private final ZonedDateTime createdAt;

    public PaymentEvent(Payment payment, ZonedDateTime createdAt) {
        this.payment = payment;
        this.createdAt = createdAt;
    }

    public Payment getPayment() {
        return payment;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
