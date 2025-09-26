package cadsok.order.domain.application.ports.input.message.listener.payment;

import cadsok.order.domain.application.models.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);

}
