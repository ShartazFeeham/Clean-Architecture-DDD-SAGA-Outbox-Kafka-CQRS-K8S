package cadsok.restaurant.domain.core.services;

import cadsok.restaurant.domain.core.entity.Order;
import cadsok.restaurant.domain.core.entity.Restaurant;
import cadsok.restaurant.domain.core.event.OrderCancelledEvent;
import cadsok.restaurant.domain.core.event.OrderCreatedEvent;
import cadsok.restaurant.domain.core.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);

}
