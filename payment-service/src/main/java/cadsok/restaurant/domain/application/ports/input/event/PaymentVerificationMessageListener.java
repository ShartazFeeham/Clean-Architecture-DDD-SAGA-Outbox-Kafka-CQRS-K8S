package cadsok.restaurant.domain.application.ports.input.event;

public interface PaymentVerificationMessageListener {

    void verificationComplete(String paymentId);

}
