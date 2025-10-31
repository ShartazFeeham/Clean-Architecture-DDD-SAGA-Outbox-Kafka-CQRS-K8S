package cadsok.order.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    // Reference IDs
    @Column(updatable = false, nullable = false)
    private UUID customerId;
    @Column(updatable = false, nullable = false)
    private UUID restaurantId;
    @Column(updatable = false, nullable = false)
    private UUID trackingId;

    // Order details: stats
    @Column(updatable = false, nullable = false)
    private String price;
    @Column(nullable = false)
    @Setter
    private Integer orderStatus;
    @OneToOne
    @NotNull
    private StreetAddressEntity deliveryAddress;

    // Order details: items
    @NotNull
    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;
    private String failureMessages;
}
