package cadsok.restaurant.domain.application.ports.output.event;

import cadsok.restaurant.domain.core.event.PaymentInfoVerificationEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface PaymentVerificationMessagePublisher extends DomainEventPublisher<PaymentInfoVerificationEvent> {
}
