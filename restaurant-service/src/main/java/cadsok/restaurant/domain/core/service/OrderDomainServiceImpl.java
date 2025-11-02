package cadsok.restaurant.domain.core.service;

import cadsok.restaurant.domain.core.event.RestaurantApprovedEvent;
import cadsok.restaurant.domain.core.event.RestaurantEvent;
import cadsok.restaurant.domain.core.event.RestaurantRejectedEvent;
import cadsok.restaurant.domain.core.exception.RestaurantDomainException;
import commonmodule.domain.values.DateTimeUtil;

public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public RestaurantEvent handleNewOrder(String orderId, boolean accept) {
        if (orderId == null) {
            throw new RestaurantDomainException("Order cannot be null");
        }
        if (accept) {
            return new RestaurantApprovedEvent(orderId, DateTimeUtil.now());
        } else {
            return new RestaurantRejectedEvent(orderId,  DateTimeUtil.now());
        }
    }
}
