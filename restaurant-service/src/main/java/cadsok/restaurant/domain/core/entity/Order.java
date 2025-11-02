package cadsok.restaurant.domain.core.entity;

import commonmodule.domain.entity.AggregateRoot;
import commonmodule.domain.values.OrderId;
import commonmodule.domain.values.RestaurantId;

public class Order extends AggregateRoot<OrderId> {
    private final RestaurantId restaurantId;

    public Order(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }
}
