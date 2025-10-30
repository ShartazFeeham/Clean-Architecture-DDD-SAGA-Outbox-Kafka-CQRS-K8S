package cadsok.payment.messaging.publisher;

import cadsok.payment.domain.application.ports.output.event.PaymentCancelMessagePublisher;
import cadsok.payment.domain.core.event.PaymentCancelledEvent;

public class PaymentCancelMessagePublisherImpl implements PaymentCancelMessagePublisher {
    @Override
    public void publish(PaymentCancelledEvent event) {

    }
}
