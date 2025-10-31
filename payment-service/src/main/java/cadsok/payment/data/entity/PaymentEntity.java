package cadsok.payment.data.entity;

import commonmodule.domain.entity.BaseEntity;
import commonmodule.domain.values.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payments")
public class PaymentEntity extends BaseEntity<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;

    private UUID orderId;
    private UUID customerId;
    private String price;
    private PaymentStatus paymentStatus;
}
