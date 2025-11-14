package cadsok.payment.data.repository;

import cadsok.payment.data.entity.PaymentOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentOutboxRepository extends JpaRepository<PaymentOutbox, UUID> {
}
