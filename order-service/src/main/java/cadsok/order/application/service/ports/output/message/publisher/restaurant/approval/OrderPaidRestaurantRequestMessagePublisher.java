package cadsok.order.application.service.ports.output.message.publisher.restaurant.approval;

import cadsok.common.domain.events.publisher.DomainEventPublisher;
import cadsok.order.domain.core.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
