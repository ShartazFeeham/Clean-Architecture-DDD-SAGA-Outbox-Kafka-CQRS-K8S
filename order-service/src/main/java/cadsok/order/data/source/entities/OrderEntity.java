package cadsok.order.data.source.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderEntity extends BaseEntityUUID {

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
    private Integer orderStatus;
    @OneToOne
    @NotNull
    private StreetAddressEntity deliveryAddress;

    // Order details: items
    @OneToMany
    @NotNull
    private List<OrderItemEntity> items;
    private String failureMessages;
}
