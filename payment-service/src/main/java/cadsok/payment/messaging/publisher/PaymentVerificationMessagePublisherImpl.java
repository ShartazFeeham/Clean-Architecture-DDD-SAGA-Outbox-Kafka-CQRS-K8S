package cadsok.payment.messaging.publisher;

import cadsok.payment.domain.application.ports.output.event.PaymentVerificationMessagePublisher;
import cadsok.payment.domain.core.event.PaymentInfoVerificationEvent;

public class PaymentVerificationMessagePublisherImpl implements PaymentVerificationMessagePublisher {
    @Override
    public void publish(PaymentInfoVerificationEvent event) {

    }
}
