package cadsok.payment.domain.application.ports.input.event;

public interface PaymentVerificationMessageListener {

    void verificationComplete(String paymentId);

}
