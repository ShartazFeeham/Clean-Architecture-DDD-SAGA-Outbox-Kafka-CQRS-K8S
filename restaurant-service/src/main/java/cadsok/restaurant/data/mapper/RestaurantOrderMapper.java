package cadsok.restaurant.data.mapper;

import cadsok.restaurant.data.entity.AcceptanceStatus;
import cadsok.restaurant.data.entity.RestaurantOrderEntity;
import cadsok.restaurant.domain.core.entity.RestaurantOrder;
import commonmodule.domain.values.OrderId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RestaurantOrderMapper {

    public RestaurantOrderEntity toDbEntity(RestaurantOrder restaurantOrder) {
        return RestaurantOrderEntity
                .builder()
                .orderId(restaurantOrder.getId().getValue())
                .detailJson(restaurantOrder.getJsonData())
                .build();
    }

    public RestaurantOrderEntity toDbEntity(String orderId, String jsonData) {
        return RestaurantOrderEntity
                .builder()
                .orderId(UUID.fromString(orderId))
                .detailJson(jsonData)
                .build();
    }

    public RestaurantOrderEntity toDbEntity(String orderId, String jsonData, AcceptanceStatus status) {
        return RestaurantOrderEntity
                .builder()
                .orderId(UUID.fromString(orderId))
                .detailJson(jsonData)
                .status(status)
                .build();
    }

    public RestaurantOrder toCoreEntity(RestaurantOrderEntity restaurantOrderEntity) {
        return new RestaurantOrder(new OrderId(restaurantOrderEntity.getOrderId()),
                restaurantOrderEntity.getDetailJson());
    }

    public RestaurantOrder toCoreEntity(String orderId, String jsonData) {
        return new RestaurantOrder(new OrderId(UUID.fromString(orderId)), jsonData);
    }
}
