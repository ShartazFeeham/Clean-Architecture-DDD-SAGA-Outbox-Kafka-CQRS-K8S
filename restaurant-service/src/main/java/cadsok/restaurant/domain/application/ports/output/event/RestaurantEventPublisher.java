package cadsok.restaurant.domain.application.ports.output.event;

import cadsok.restaurant.domain.core.event.RestaurantEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;

public interface RestaurantEventPublisher extends DomainEventPublisher<RestaurantEvent> {
}
