package cadsok.payment.domain.application.ports.output.event;

import cadsok.payment.domain.core.event.PaymentInitializedEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface PaymentProcessingMessagePublisher extends DomainEventPublisher<PaymentInitializedEvent> {
}
