package cadsok.restaurant.domain.application.services;

import cadsok.restaurant.domain.core.event.PaymentEvent;
import commonmodule.domain.events.publisher.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<PaymentEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(PaymentEvent event) {
        this.applicationEventPublisher.publishEvent(event);
        log.info("Payment event has been published for payment id: {}", event.getPayment().getId().getValue());
    }
}
