package cadsok.restaurant.domain.application.ports.output.event;

import cadsok.restaurant.domain.core.event.PaymentCompleteEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface PaymentCompleteMessagePublisher extends DomainEventPublisher<PaymentCompleteEvent> {
}
