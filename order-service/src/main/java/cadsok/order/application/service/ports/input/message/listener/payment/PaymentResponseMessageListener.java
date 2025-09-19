package cadsok.order.application.service.ports.input.message.listener.payment;

import cadsok.order.application.service.models.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);

}
