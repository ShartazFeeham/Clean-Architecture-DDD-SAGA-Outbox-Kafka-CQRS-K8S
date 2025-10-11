package cadsok.restaurant.data.adapter;

import cadsok.restaurant.domain.application.ports.output.repository.RestaurantRepository;
import cadsok.restaurant.data.entities.RestaurantEntity;
import cadsok.restaurant.data.mapper.RestaurantMapper;
import cadsok.restaurant.data.repositories.RestaurantJpaRepository;
import cadsok.restaurant.domain.core.entity.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJpaRepository restaurantJpaRepository;

    public RestaurantRepositoryImpl(RestaurantJpaRepository restaurantJpaRepository) {
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        Optional<RestaurantEntity> restaurantEntity = restaurantJpaRepository.findById(restaurant.getId().getValue());
        if (restaurantEntity.isEmpty()) {
            return Optional.empty();
        }
        return restaurantEntity.map(RestaurantMapper::toRestaurant);
    }
}
