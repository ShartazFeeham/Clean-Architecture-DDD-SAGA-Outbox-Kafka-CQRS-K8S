package cadsok.order.application.service.ports.output.message.publisher.payment;

import commonmodule.domain.events.publisher.DomainEventPublisher;
import cadsok.order.domain.core.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
