package cadsok.payment.domain.application.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentCancelRequestDto {
    private final String paymentId;
}
