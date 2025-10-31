package cadsok.order.domain.application.ports.output.message.publisher.payment;

import commonmodule.domain.events.publisher.DomainEventPublisher;
import cadsok.order.domain.core.event.OrderCancelledEvent;

public interface OrderCancelledMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
