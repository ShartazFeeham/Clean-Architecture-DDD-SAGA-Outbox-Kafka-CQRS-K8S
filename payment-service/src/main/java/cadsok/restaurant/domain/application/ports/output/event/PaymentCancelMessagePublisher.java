package cadsok.restaurant.domain.application.ports.output.event;

import cadsok.restaurant.domain.core.event.PaymentCancelledEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface PaymentCancelMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}
