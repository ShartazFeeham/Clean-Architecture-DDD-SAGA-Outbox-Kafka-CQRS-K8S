package cadsok.payment.messaging.publisher;

import cadsok.payment.domain.application.ports.output.event.PaymentVerificationMessagePublisher;
import cadsok.payment.domain.core.event.PaymentInfoInitializedEvent;

public class PaymentVerificationMessagePublisherImpl implements PaymentVerificationMessagePublisher {
    @Override
    public void publish(PaymentInfoInitializedEvent event) {

    }
}
