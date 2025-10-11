package cadsok.restaurant.domain.application.ports.output.message.publisher.payment;

import commonmodule.domain.events.publisher.DomainEventPublisher;
import cadsok.restaurant.domain.core.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
