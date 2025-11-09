package cadsok.restaurant.domain.application.services.events.base;

import cadsok.restaurant.domain.core.event.RestaurantEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;
import commonmodule.infra.logging.LogAction;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class RestaurantApplicationInternalDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<RestaurantEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @LogAction("Publishing payment event internally")
    public void publish(RestaurantEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }
}
