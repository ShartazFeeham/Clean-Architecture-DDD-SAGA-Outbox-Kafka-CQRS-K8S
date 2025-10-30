package cadsok.payment.domain.application.ports.output.event;

import cadsok.payment.domain.core.event.PaymentInfoInitializedEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface PaymentVerificationMessagePublisher extends DomainEventPublisher<PaymentInfoInitializedEvent> {
}
