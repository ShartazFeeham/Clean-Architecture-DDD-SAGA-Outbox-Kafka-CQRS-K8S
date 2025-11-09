package cadsok.restaurant.domain.core.service;

import cadsok.restaurant.domain.core.event.RestaurantApprovedEvent;
import cadsok.restaurant.domain.core.event.RestaurantEvent;
import cadsok.restaurant.domain.core.event.RestaurantRejectedEvent;
import cadsok.restaurant.domain.core.exception.RestaurantDomainException;
import commonmodule.domain.values.DateTimeUtil;
import io.micrometer.common.util.StringUtils;

import java.util.Objects;

public class RestaurantDomainServiceImpl implements RestaurantDomainService {

    @Override
    public void validateNewOrder(String orderId, String jsonData) {
        if (Objects.isNull(orderId) || StringUtils.isBlank(jsonData)) {
            throw new RestaurantDomainException("Order id can nt be null & order data can not be empty");
        }
    }

    @Override
    public RestaurantEvent handleRestaurantAction(String orderId, boolean accept) {
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
