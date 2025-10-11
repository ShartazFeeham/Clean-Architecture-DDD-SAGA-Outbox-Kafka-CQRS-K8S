package cadsok.restaurant.domain.application.ports.input.client;

import cadsok.restaurant.domain.application.models.PaymentCancelRequestDto;
import cadsok.restaurant.domain.application.models.PaymentCreateRequestDto;
import cadsok.restaurant.domain.application.models.PaymentResponseDto;

public interface PaymentApplicationService {

    PaymentResponseDto info(String paymentId);

    PaymentResponseDto initPay(PaymentCreateRequestDto paymentCreateRequestDto);

    PaymentResponseDto completePay(String paymentId);

    PaymentResponseDto cancel(PaymentCancelRequestDto paymentCancelRequestDto);

}
