package cadsok.order.domain.application.ports.output.message.publisher.payment;

import cadsok.order.domain.core.event.OrderPaymentValidEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface OrderPaymentValidatedMessagePublisher extends DomainEventPublisher<OrderPaymentValidEvent> {
}
