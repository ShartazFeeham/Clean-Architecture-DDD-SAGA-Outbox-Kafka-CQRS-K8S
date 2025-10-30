package cadsok.payment.domain.application.ports.input.client;

import cadsok.payment.domain.application.models.PaymentCancelRequestDto;
import cadsok.payment.domain.application.models.PaymentCreateRequestDto;
import cadsok.payment.domain.application.models.PaymentResponseDto;

public interface PaymentApplicationService {

    PaymentResponseDto info(String paymentId);

    PaymentResponseDto initPay(PaymentCreateRequestDto paymentCreateRequestDto);

    PaymentResponseDto completePay(String paymentId);

    PaymentResponseDto cancel(PaymentCancelRequestDto paymentCancelRequestDto);

}
