package cadsok.restaurant.domain.core.entity;

import commonmodule.domain.entity.AggregateRoot;
import commonmodule.domain.values.OrderId;

public class RestaurantOrder extends AggregateRoot<OrderId> {
    private final String jsonData;

    public RestaurantOrder(OrderId orderId, String jsonData) {
        super.setId(orderId);
        this.jsonData = jsonData;
    }

    public String getJsonData() {
        return jsonData;
    }
}
