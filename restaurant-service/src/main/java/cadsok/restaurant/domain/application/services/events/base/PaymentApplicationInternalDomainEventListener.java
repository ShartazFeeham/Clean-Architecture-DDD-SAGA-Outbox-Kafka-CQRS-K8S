package cadsok.restaurant.domain.application.services.events.base;

import cadsok.restaurant.domain.application.ports.output.event.RestaurantEventPublisher;
import cadsok.restaurant.domain.core.event.RestaurantEvent;
import commonmodule.infra.logging.LogAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentApplicationInternalDomainEventListener {

    private final RestaurantEventPublisher restaurantEventPublisher;

    @TransactionalEventListener
    @LogAction("Sending restaurant event to message broker.")
    void process(RestaurantEvent event) {
        restaurantEventPublisher.publish(event);
    }
}
