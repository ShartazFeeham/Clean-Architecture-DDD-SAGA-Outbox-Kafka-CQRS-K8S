package cadsok.order.domain.application.services;

import commonmodule.domain.events.publisher.DomainEventPublisher;
import commonmodule.infra.logging.LogAction;
import cadsok.order.domain.core.event.OrderCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<OrderCreatedEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @LogAction("Publishing order created event internally")
    public void publish(OrderCreatedEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }
}
