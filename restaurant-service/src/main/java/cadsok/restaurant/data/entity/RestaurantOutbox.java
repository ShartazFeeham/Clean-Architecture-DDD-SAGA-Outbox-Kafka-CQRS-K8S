package cadsok.restaurant.data.entity;

import commonmodule.messaging.OutboxBaseEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "outbox")
@NoArgsConstructor
public class RestaurantOutbox extends OutboxBaseEntity {

    public static RestaurantOutbox create(String payload, String aggregateType, String aggregateId, String type) {
        RestaurantOutbox outbox = new RestaurantOutbox();
        outbox.setAggregateType(aggregateType);
        outbox.setAggregateId(aggregateId);
        outbox.setPayload(payload);
        outbox.setType(type);
        return outbox;
    }

}
