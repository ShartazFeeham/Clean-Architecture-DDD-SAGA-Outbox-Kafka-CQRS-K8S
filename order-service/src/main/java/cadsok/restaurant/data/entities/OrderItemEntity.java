package cadsok.restaurant.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderItemEntity extends BaseEntity {
    @Column(updatable = false, nullable = false)
    private UUID productId;
    private String productName;
    private String productPrice;
    @Column(updatable = false, nullable = false)
    private Integer quantity;
    @Column(updatable = false, nullable = false)
    private String price;
    @Column(updatable = false, nullable = false)
    private String subTotal;
    @ManyToOne
    private OrderEntity order;
}
