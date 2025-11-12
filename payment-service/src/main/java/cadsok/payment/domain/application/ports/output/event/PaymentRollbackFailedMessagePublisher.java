package cadsok.payment.domain.application.ports.output.event;

import cadsok.payment.domain.core.event.PaymentRollbackFailedEvent;
import cadsok.payment.domain.core.event.PaymentRollbackSucceedEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface PaymentRollbackFailedMessagePublisher extends DomainEventPublisher<PaymentRollbackFailedEvent> {
}
