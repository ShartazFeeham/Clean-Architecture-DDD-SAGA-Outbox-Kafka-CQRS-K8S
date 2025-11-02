package cadsok.restaurant.domain.core.service;

import cadsok.restaurant.domain.core.event.RestaurantEvent;

public interface OrderDomainService {

    RestaurantEvent handleNewOrder(String orderId, boolean accept);

}
