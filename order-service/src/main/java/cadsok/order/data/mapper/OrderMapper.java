package cadsok.order.data.mapper;

import cadsok.order.data.entities.OrderEntity;
import cadsok.order.data.entities.StreetAddressEntity;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.values.StreetAddress;
import cadsok.order.domain.core.values.TrackingId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonmodule.domain.values.CustomerId;
import commonmodule.domain.values.OrderId;
import commonmodule.domain.values.RestaurantId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderMapper {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(OrderMapper.class);
    
    public static OrderEntity toOrderEntity(Order order, StreetAddressEntity streetAddressEntity) {
        return OrderEntity.builder()
                .customerId(order.getCustomerId().getValue())
                .restaurantId(order.getRestaurantId().getValue())
                .trackingId(order.getTrackingId().getValue())
                .price(order.getPrice().getAmount().toString())
                .orderStatus(OrderStatusMapper.toDbStatus(order.getOrderStatus()))
                .deliveryAddress(
//                        StreetAddressEntity.builder()
//                                .city(order.getDeliveryAddress().getCity())
//                                .postalCode(order.getDeliveryAddress().getPostalCode())
//                                .street(order.getDeliveryAddress().getStreet())
//                                .build()
                        streetAddressEntity
                )
                .items(order.getItems().stream().map(OrderItemMapper::toOrderItemEntity).toList())
                .failureMessages(getFailureMessagesAsString(order.getFailureMessages()))
                .build();
    }

    private static String getFailureMessagesAsString(List<String> failureMessages) {
        if (failureMessages == null || failureMessages.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(failureMessages);
        } catch (JsonProcessingException e) {
            log.error("Could not convert failure messages to string: {}", e.getMessage());
            return null;
        }
    }
    
    public static Order toOrder(OrderEntity orderEntity) {
        return Order.Builder.builder()
                .id(new OrderId(orderEntity.getId()))
                .customerId(new CustomerId(orderEntity.getCustomerId()))
                .restaurantId(new RestaurantId(orderEntity.getRestaurantId()))
                .trackingId(new TrackingId(orderEntity.getTrackingId()))
                .price(new commonmodule.domain.values.Money(new java.math.BigDecimal(orderEntity.getPrice())))
                .orderStatus(OrderStatusMapper.toDomainStatus(orderEntity.getOrderStatus()))
                .deliveryAddress(
                        new StreetAddress(
                                orderEntity.getDeliveryAddress().getStreet(),
                                orderEntity.getDeliveryAddress().getPostalCode(),
                                orderEntity.getDeliveryAddress().getCity()
                        )
                )
                .items(orderEntity.getItems().stream().map(OrderItemMapper::toOrderItem).toList())
                .failureMessages(getFailureMessagesFromString(orderEntity.getFailureMessages()))
                .build();
    }

    @SuppressWarnings("unchecked")
    private static List<String> getFailureMessagesFromString(String failureMessages) {
        if (failureMessages == null || failureMessages.isEmpty()) {
            return List.of();
        }
        try {
            return objectMapper.readValue(failureMessages, List.class);
        } catch (JsonProcessingException e) {
            log.error("Could not convert failure messages from string: {}", e.getMessage());
            return List.of();
        }
    }
}
