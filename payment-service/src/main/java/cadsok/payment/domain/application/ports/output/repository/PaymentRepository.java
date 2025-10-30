package cadsok.payment.domain.application.ports.output.repository;

import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.values.PaymentId;

import java.util.Optional;

public interface PaymentRepository {

    Optional<Payment> getPaymentById(PaymentId paymentId);

    Payment savePayment(Payment payment);

}
