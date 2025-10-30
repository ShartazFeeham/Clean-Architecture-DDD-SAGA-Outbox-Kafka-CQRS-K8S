package cadsok.payment.domain.application.ports.output.event;

import cadsok.payment.domain.core.event.PaymentCancelledEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface PaymentCancelMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}
