package cadsok.payment.domain.application.ports.output.event;

import cadsok.payment.domain.core.event.PaymentCompleteEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface PaymentCompleteMessagePublisher extends DomainEventPublisher<PaymentCompleteEvent> {
}
