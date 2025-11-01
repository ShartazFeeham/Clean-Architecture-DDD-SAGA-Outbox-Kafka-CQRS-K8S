package cadsok.order.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
