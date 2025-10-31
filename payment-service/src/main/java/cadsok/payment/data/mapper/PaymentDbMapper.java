package cadsok.payment.data.mapper;

import cadsok.payment.data.entity.PaymentEntity;
import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.values.PaymentId;
import commonmodule.domain.values.CustomerId;
import commonmodule.domain.values.Money;
import commonmodule.domain.values.OrderId;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentDbMapper {

    public static PaymentEntity toEntity(Payment payment) {
        return PaymentEntity.builder()
                .orderId(UUID.fromString(payment.getOrderId().getValue().toString()))
                .customerId(UUID.fromString(payment.getCustomerId().getValue().toString()))
                .price(payment.getPrice().getAmount().toString())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    public static Payment toPayment(PaymentEntity paymentEntity) {
        return new Payment(
                new PaymentId(paymentEntity.getPaymentId()),
                new OrderId(paymentEntity.getOrderId()),
                new CustomerId(paymentEntity.getCustomerId()),
                new Money(new BigDecimal(paymentEntity.getPrice())),
                paymentEntity.getPaymentStatus()
        );
    }

}
