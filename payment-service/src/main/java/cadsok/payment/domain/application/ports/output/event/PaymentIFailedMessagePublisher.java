package cadsok.payment.domain.application.ports.output.event;

import cadsok.payment.domain.core.event.PaymentFailedEvent;
import cadsok.payment.domain.core.event.PaymentInitializedEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface PaymentIFailedMessagePublisher extends DomainEventPublisher<PaymentFailedEvent> {
}
