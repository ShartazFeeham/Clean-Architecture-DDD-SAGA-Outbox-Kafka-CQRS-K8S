package cadsok.payment.messaging.publisher;

import cadsok.payment.domain.application.ports.output.event.PaymentInitializedMessagePublisher;
import cadsok.payment.domain.core.event.PaymentInitializedEvent;

public class PaymentInitializedMessagePublisherImpl implements PaymentInitializedMessagePublisher {
    @Override
    public void publish(PaymentInitializedEvent event) {

    }
}
