package cadsok.payment.messaging.publisher;

import cadsok.payment.domain.application.ports.output.event.PaymentProcessingMessagePublisher;
import cadsok.payment.domain.core.event.PaymentInitializedEvent;

public class PaymentProcessingMessagePublisherImpl implements PaymentProcessingMessagePublisher {
    @Override
    public void publish(PaymentInitializedEvent event) {

    }
}
