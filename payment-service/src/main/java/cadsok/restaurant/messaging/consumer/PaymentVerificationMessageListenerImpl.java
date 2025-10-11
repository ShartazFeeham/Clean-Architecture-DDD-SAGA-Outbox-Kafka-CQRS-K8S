package cadsok.restaurant.messaging.consumer;

import cadsok.restaurant.domain.application.ports.input.event.PaymentVerificationMessageListener;

public class PaymentVerificationMessageListenerImpl implements PaymentVerificationMessageListener {
    @Override
    public void verificationComplete(String paymentId) {

    }
}
