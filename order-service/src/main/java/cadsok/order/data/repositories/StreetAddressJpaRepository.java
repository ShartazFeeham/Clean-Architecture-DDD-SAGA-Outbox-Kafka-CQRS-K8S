package cadsok.order.data.repositories;

import cadsok.order.data.entities.StreetAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StreetAddressJpaRepository extends JpaRepository<StreetAddressEntity, UUID> {
}
