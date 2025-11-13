package cadsok.restaurant.data.repository;

import cadsok.restaurant.data.entity.RestaurantOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantOutboxRepository extends JpaRepository<RestaurantOutbox, UUID> {
    
}
