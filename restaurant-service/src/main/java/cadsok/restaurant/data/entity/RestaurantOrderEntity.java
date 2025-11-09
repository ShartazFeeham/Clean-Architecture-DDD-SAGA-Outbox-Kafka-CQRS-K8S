package cadsok.restaurant.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "restaurant_orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantOrderEntity {
    @Id
    @Column(updatable = false, nullable = false)
    private UUID orderId;
    @NotNull
    @Size(min = 1, max = 1_000_000_000)
    private String detailJson;
    @NotNull
    private AcceptanceStatus status;
}
