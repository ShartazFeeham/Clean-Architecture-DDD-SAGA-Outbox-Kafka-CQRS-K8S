package cadsok.payment.domain.application.services;

import cadsok.payment.domain.core.event.PaymentEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;
import commonmodule.infra.logging.LogAction;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<PaymentEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @LogAction("Publishing order created event internally")
    public void publish(PaymentEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }
}
