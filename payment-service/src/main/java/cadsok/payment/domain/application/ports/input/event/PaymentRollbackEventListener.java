package cadsok.payment.domain.application.ports.input.event;

public interface PaymentRollbackEventListener {

    void rollbackPayment(String paymentId);

}
