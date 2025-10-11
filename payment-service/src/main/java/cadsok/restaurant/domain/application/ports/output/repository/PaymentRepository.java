package cadsok.restaurant.domain.application.ports.output.repository;

import cadsok.restaurant.domain.core.entity.Payment;
import cadsok.restaurant.domain.core.values.PaymentId;

import java.util.Optional;

public interface PaymentRepository {

    Optional<Payment> getPaymentById(PaymentId paymentId);

    Payment savePayment(Payment payment);

}
