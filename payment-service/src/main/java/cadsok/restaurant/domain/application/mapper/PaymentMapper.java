package cadsok.restaurant.domain.application.mapper;

import cadsok.restaurant.domain.application.models.PaymentCreateRequestDto;
import cadsok.restaurant.domain.application.models.PaymentResponseDto;
import cadsok.restaurant.domain.core.entity.Payment;
import commonmodule.domain.values.CustomerId;
import commonmodule.domain.values.Money;
import commonmodule.domain.values.OrderId;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentMapper {

    public static Payment toPayment(PaymentCreateRequestDto request) {
        return new Payment(new OrderId(UUID.fromString(request.getOrderId())),
                new CustomerId(UUID.fromString(request.getCustomerId())),
                new Money(new BigDecimal(request.getPrice())));
    }

    public static PaymentResponseDto toResponse(Payment payment) {
        return PaymentResponseDto.builder()
                .paymentId(payment.getPaymentId().getValue().toString())
                .orderId(payment.getOrderId().getValue().toString())
                .customerId(payment.getCustomerId().getValue().toString())
                .price(payment.getPrice().getAmount().toString())
                .paymentStatus(payment.getPaymentStatus().toString())
                .build();
    }
}
