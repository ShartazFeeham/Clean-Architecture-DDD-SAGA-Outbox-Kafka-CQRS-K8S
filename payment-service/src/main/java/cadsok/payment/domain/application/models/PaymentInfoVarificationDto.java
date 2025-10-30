package cadsok.payment.domain.application.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentInfoVarificationDto {
    private final String paymentId;
    private final Boolean isValid;
}
