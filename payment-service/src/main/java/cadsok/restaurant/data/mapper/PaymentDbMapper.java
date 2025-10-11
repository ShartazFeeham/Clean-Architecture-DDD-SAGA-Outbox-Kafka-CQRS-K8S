package cadsok.restaurant.data.mapper;

import cadsok.restaurant.data.entity.PaymentEntity;
import cadsok.restaurant.domain.core.entity.Payment;
import commonmodule.domain.values.CustomerId;
import commonmodule.domain.values.Money;
import commonmodule.domain.values.OrderId;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentDbMapper {

    public static PaymentEntity toEntity(Payment payment) {
        return PaymentEntity.builder()
                .orderId(UUID.fromString(payment.getOrderId().toString()))
                .customerId(UUID.fromString(payment.getCustomerId().toString()))
                .price(payment.getPrice().getAmount().toString())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    public static Payment toPayment(PaymentEntity paymentEntity) {
        return new Payment(new OrderId(paymentEntity.getOrderId()),
                new CustomerId(paymentEntity.getCustomerId()),
                new Money(new BigDecimal(paymentEntity.getPrice())));
    }

}
