package cadsok.payment.messaging.publisher;

import cadsok.payment.domain.application.ports.output.event.PaymentIFailedMessagePublisher;
import cadsok.payment.domain.core.event.PaymentInitializedEvent;

public class PaymentFailedMessagePublisherImpl implements PaymentIFailedMessagePublisher {
    @Override
    public void publish(PaymentInitializedEvent event) {

    }
}
