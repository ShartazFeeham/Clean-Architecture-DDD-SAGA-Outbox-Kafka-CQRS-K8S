package cadsok.order.data.source.adapter;

import cadsok.order.application.service.ports.output.repository.RestaurantRepository;
import cadsok.order.data.source.entities.RestaurantEntity;
import cadsok.order.data.source.mapper.RestaurantMapper;
import cadsok.order.data.source.repositories.RestaurantJpaRepository;
import cadsok.order.domain.core.entity.Restaurant;
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
