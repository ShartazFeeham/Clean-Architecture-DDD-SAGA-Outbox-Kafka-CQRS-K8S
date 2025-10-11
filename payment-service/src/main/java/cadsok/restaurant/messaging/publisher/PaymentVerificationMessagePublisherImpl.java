package cadsok.restaurant.messaging.publisher;

import cadsok.restaurant.domain.application.ports.output.event.PaymentVerificationMessagePublisher;
import cadsok.restaurant.domain.core.event.PaymentInfoVerificationEvent;

public class PaymentVerificationMessagePublisherImpl implements PaymentVerificationMessagePublisher {
    @Override
    public void publish(PaymentInfoVerificationEvent event) {

    }
}
