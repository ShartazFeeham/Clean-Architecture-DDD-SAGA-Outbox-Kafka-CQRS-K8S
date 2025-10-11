package cadsok.restaurant.domain.application.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PaymentCreateRequestDto {
    private final String orderId;
    private final String customerId;
    private final String price;
}
