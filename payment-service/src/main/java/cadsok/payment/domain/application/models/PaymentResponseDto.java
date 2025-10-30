package cadsok.payment.domain.application.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PaymentResponseDto {
    private final String paymentId;
    private final String orderId;
    private final String customerId;
    private final String price;
    private final String paymentStatus;
}
