package cadsok.order.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "restaurant_entity_products",
            joinColumns = @JoinColumn(name = "restaurant_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private List<ProductEntity> products;
    private boolean active;
}
