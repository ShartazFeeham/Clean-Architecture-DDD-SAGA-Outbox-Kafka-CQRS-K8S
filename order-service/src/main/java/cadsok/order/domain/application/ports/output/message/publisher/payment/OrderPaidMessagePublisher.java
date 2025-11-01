package cadsok.order.domain.application.ports.output.message.publisher.payment;

import cadsok.order.domain.core.event.OrderPaidEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface OrderPaidMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
