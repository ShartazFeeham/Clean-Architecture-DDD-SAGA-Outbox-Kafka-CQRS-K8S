package cadsok.order.application.service.ports.output.repository;

import commonmodule.domain.values.RestaurantId;
import cadsok.order.domain.core.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);

}
