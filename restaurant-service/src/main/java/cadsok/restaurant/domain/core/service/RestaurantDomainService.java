package cadsok.restaurant.domain.core.service;

import cadsok.restaurant.domain.core.event.RestaurantEvent;


public interface RestaurantDomainService {

    void validateNewOrder(String orderId, String jsonData);

    RestaurantEvent handleRestaurantAction(String orderId, boolean accept);

}
