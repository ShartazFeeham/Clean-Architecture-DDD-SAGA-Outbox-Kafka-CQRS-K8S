package cadsok.restaurant.messaging.publisher;

import cadsok.restaurant.domain.application.ports.output.event.PaymentCancelMessagePublisher;
import cadsok.restaurant.domain.core.event.PaymentCancelledEvent;

public class PaymentCancelMessagePublisherImpl implements PaymentCancelMessagePublisher {
    @Override
    public void publish(PaymentCancelledEvent event) {

    }
}
