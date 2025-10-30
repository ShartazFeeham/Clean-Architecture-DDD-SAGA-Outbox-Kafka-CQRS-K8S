package cadsok.payment.domain.application.ports.input.client;

import cadsok.payment.domain.application.models.PaymentCreateRequestDto;
import cadsok.payment.domain.application.models.PaymentTrackingResponseDto;

public interface PaymentApplicationService {

    PaymentTrackingResponseDto track(String paymentId);

    PaymentTrackingResponseDto initializePayment(PaymentCreateRequestDto paymentCreateRequestDto);

}
