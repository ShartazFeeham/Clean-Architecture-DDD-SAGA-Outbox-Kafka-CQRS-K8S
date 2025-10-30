package cadsok.payment.domain.application.models;

import lombok.Builder;

@Builder
public record PaymentTrackingResponseDto(String paymentId, String orderId, String customerId, String price,
                                         String paymentStatus) {
}
