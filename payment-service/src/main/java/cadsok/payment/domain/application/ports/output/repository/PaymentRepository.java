package cadsok.payment.domain.application.ports.output.repository;

import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.values.PaymentId;
import commonmodule.domain.values.OrderId;

import java.util.Optional;

public interface PaymentRepository {

    Optional<Payment> getPaymentById(PaymentId paymentId);

    Payment savePayment(Payment payment);

    Payment updatePayment(Payment payment);

    Optional<Payment> getPaymentByOrderId(OrderId orderId);
}
