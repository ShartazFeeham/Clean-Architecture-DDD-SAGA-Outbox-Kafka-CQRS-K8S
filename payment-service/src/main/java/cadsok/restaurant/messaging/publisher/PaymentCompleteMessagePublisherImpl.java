package cadsok.restaurant.messaging.publisher;

import cadsok.restaurant.domain.application.ports.output.event.PaymentCompleteMessagePublisher;
import cadsok.restaurant.domain.core.event.PaymentCompleteEvent;

public class PaymentCompleteMessagePublisherImpl implements PaymentCompleteMessagePublisher {
    @Override
    public void publish(PaymentCompleteEvent event) {

    }
}
