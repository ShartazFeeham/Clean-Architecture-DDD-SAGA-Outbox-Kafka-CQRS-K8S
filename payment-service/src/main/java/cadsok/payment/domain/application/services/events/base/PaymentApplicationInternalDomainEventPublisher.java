package cadsok.payment.domain.application.services.events.base;

import cadsok.payment.domain.core.event.PaymentEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;
import commonmodule.infra.logging.LogAction;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class PaymentApplicationInternalDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<PaymentEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @LogAction("Publishing payment event internally")
    public void publish(PaymentEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }
}
