package cadsok.order.domain.application.ports.input.message.listener.payment;

import cadsok.order.domain.application.models.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentValidation(String orderId, String price, String paymentId);

    void paymentCancelled(PaymentResponse paymentResponse);

}
