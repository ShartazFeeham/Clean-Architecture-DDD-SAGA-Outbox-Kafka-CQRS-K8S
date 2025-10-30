package cadsok.payment.data.entity;

import commonmodule.domain.entity.BaseEntity;
import commonmodule.domain.values.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class PaymentEntity extends BaseEntity<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID paymentId;

    private final UUID orderId;
    private final UUID customerId;
    private final String price;
    private PaymentStatus paymentStatus;
}
