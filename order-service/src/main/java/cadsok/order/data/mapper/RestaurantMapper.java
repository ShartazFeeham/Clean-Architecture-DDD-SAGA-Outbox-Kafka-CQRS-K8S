package cadsok.order.data.mapper;

import cadsok.order.data.entities.RestaurantEntity;
import cadsok.order.domain.core.entity.Restaurant;
import commonmodule.domain.values.RestaurantId;

import java.util.Objects;
import java.util.stream.Collectors;

public class RestaurantMapper {

    public static RestaurantEntity toRestaurantEntity(Restaurant restaurant) {
        return RestaurantEntity.builder()
                .id(restaurant.getId().getValue())
                .products(
                        restaurant.getProducts().stream().filter(Objects::isNull)
                                .map(ProductMapper::toProductEntity).collect(Collectors.toList())
                )
                .active(restaurant.isActive())
                .build();
    }

    public static Restaurant toRestaurant(RestaurantEntity restaurantEntity) {
        return Restaurant.Builder.builder()
                .id(new RestaurantId(restaurantEntity.getId()))
                .products(
                        restaurantEntity.getProducts().stream().filter(Objects::isNull)
                                .map(ProductMapper::toProduct).collect(Collectors.toList())
                )
                .active(restaurantEntity.isActive())
                .build();
    }

}
