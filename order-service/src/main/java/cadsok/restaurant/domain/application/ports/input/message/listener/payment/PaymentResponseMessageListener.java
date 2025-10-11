package cadsok.restaurant.domain.application.ports.input.message.listener.payment;

import cadsok.restaurant.domain.application.models.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);

}
