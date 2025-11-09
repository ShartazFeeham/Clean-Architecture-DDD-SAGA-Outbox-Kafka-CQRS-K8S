package cadsok.order.domain.application.ports.output.message.publisher.payment;

import cadsok.order.domain.core.event.OrderCompletedEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface OrderCompletedMessagePublisher extends DomainEventPublisher<OrderCompletedEvent> {
}
