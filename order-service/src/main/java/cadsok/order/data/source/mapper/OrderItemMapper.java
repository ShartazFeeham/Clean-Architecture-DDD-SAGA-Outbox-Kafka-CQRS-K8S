package cadsok.order.data.source.mapper;

import cadsok.order.data.source.entities.OrderItemEntity;
import cadsok.order.domain.core.entity.OrderItem;
import cadsok.order.domain.core.entity.Product;
import cadsok.order.domain.core.values.OrderItemId;
import commonmodule.domain.values.Money;
import commonmodule.domain.values.ProductId;

import java.math.BigDecimal;

public class OrderItemMapper {

    public static OrderItemEntity toOrderItemEntity(OrderItem orderItem) {
        return OrderItemEntity.builder()
                .productId(orderItem.getProduct().getId().getValue())
                .productName(orderItem.getProduct().getName())
                .productPrice(orderItem.getProduct().getPrice().getAmount().toString())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice().getAmount().toString())
                .subTotal(orderItem.getSubTotal().getAmount().toString())
                .build();
    }

    public static OrderItem toOrderItem(OrderItemEntity orderItemEntity) {
        return OrderItem.Builder.builder()
                .id(new OrderItemId(orderItemEntity.getId()))
                .product(
                        new Product(
                                new ProductId(orderItemEntity.getProductId()),
                                orderItemEntity.getProductName(),
                                new Money(new BigDecimal(orderItemEntity.getProductPrice()))
                        )
                )
                .quantity(orderItemEntity.getQuantity())
                .subTotal(new Money(new BigDecimal(orderItemEntity.getSubTotal())))
                .build();
    }
}
