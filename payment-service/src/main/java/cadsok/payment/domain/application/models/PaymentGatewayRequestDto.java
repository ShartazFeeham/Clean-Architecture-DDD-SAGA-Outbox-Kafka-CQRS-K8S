package cadsok.payment.domain.application.models;

import commonmodule.domain.values.PaymentStatus;

public record PaymentGatewayRequestDto(String paymentId, PaymentStatus paymentStatus) {
}
