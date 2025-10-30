package cadsok.payment.domain.application.ports.input.event;

import cadsok.payment.domain.application.models.PaymentInfoVarificationDto;

public interface PaymentVerificationMessageListener {

    void handleVerificationResponse(PaymentInfoVarificationDto paymentInfoVarificationDto);

}
