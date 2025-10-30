package cadsok.payment.messaging.consumer;

import cadsok.payment.domain.application.ports.input.event.PaymentVerificationMessageListener;

public class PaymentVerificationMessageListenerImpl implements PaymentVerificationMessageListener {
    @Override
    public void verificationComplete(String paymentId) {

    }
}
