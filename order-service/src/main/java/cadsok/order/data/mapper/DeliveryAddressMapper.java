package cadsok.order.data.mapper;

import cadsok.order.data.entities.StreetAddressEntity;
import cadsok.order.domain.core.values.StreetAddress;

public class DeliveryAddressMapper {

    public static StreetAddressEntity toEntity(StreetAddress deliveryAddress) {
        if (deliveryAddress == null) {
            return null;
        }
        return StreetAddressEntity.builder()
                .street(deliveryAddress.getStreet())
                .city(deliveryAddress.getCity())
                .postalCode(deliveryAddress.getPostalCode())
                .build();
    }

    public static StreetAddress toDomain(StreetAddressEntity entity) {
        if (entity == null) {
            return null;
        }
        return new StreetAddress(
                entity.getId(),
                entity.getStreet(),
                entity.getCity(),
                entity.getPostalCode()
        );
    }

}
