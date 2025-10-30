package cadsok.payment.domain.application.ports.input.event;

import cadsok.payment.domain.application.models.PaymentGatewayRequestDto;

public interface PaymentGatewayMessageListener {

    void handlePaymentGatewayResponse(PaymentGatewayRequestDto paymentGatewayRequestDto);

}
