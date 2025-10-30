package cadsok.payment.domain.application.models;

import lombok.Builder;

@Builder
public record PaymentCreateRequestDto(String orderId, String customerId, String price) {
}
