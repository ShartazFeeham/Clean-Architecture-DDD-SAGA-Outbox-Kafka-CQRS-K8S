package cadsok.payment.domain.application.mapper;

import cadsok.payment.domain.application.models.PaymentCreateRequestDto;
import cadsok.payment.domain.application.models.PaymentTrackingResponseDto;
import cadsok.payment.domain.core.entity.Payment;
import commonmodule.domain.values.CustomerId;
import commonmodule.domain.values.Money;
import commonmodule.domain.values.OrderId;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentMapper {

    public static Payment toPayment(PaymentCreateRequestDto request) {
        return new Payment(null, new OrderId(UUID.fromString(request.orderId())),
                new CustomerId(UUID.fromString(request.customerId())),
                new Money(new BigDecimal(request.amount())), null);
    }

    public static PaymentTrackingResponseDto toResponse(Payment payment) {
        return PaymentTrackingResponseDto.builder()
                .paymentId(payment.getPaymentId().getValue().toString())
                .orderId(payment.getOrderId().getValue().toString())
                .customerId(payment.getCustomerId().getValue().toString())
                .price(payment.getPrice().getAmount().toString())
                .paymentStatus(payment.getPaymentStatus().toString())
                .build();
    }
}
