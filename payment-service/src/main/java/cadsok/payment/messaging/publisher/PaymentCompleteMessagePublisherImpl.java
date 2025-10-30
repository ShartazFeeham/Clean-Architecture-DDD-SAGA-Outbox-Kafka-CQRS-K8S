package cadsok.payment.messaging.publisher;

import cadsok.payment.domain.application.ports.output.event.PaymentCompleteMessagePublisher;
import cadsok.payment.domain.core.event.PaymentCompleteEvent;

public class PaymentCompleteMessagePublisherImpl implements PaymentCompleteMessagePublisher {
    @Override
    public void publish(PaymentCompleteEvent event) {

    }
}
