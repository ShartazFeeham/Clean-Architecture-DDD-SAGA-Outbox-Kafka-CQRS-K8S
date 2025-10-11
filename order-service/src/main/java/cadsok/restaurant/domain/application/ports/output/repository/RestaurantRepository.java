package cadsok.restaurant.domain.application.ports.output.repository;

import cadsok.restaurant.domain.core.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);

}
