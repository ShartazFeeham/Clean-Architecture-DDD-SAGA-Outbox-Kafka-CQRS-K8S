package cadsok.restaurant.data.repository;

import cadsok.restaurant.data.entity.AcceptanceStatus;
import cadsok.restaurant.data.entity.RestaurantOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantOrderJpaRepository extends JpaRepository<RestaurantOrderEntity, UUID> {

    List<RestaurantOrderEntity> findByStatus(AcceptanceStatus status);

}
