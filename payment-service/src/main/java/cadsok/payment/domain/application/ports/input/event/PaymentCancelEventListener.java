package cadsok.payment.domain.application.ports.input.event;

import cadsok.payment.domain.application.models.PaymentCancelRequestDto;

public interface PaymentCancelEventListener {

    void handlePaymentCancelEvent(PaymentCancelRequestDto paymentCancelRequestDto);

}
