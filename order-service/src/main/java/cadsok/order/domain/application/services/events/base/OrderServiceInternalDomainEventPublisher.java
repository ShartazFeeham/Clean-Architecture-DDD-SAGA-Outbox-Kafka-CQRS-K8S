package cadsok.order.domain.application.services.events.base;

import cadsok.order.domain.core.event.OrderEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;
import commonmodule.infra.logging.LogAction;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceInternalDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<OrderEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @LogAction("Publishing order created event internally")
    public void publish(OrderEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }
}
