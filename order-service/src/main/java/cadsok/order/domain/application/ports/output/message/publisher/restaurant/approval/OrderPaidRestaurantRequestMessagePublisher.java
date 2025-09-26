package cadsok.order.domain.application.ports.output.message.publisher.restaurant.approval;

import commonmodule.domain.events.publisher.DomainEventPublisher;
import cadsok.order.domain.core.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
