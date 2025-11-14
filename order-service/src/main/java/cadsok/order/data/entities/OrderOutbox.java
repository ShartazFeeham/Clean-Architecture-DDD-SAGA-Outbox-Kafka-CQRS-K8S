package cadsok.order.data.entities;

import commonmodule.messaging.OutboxBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "outbox")
@NoArgsConstructor
public class OrderOutbox extends OutboxBaseEntity {

    public static OrderOutbox create(String payload, String aggregateType, String aggregateId, String type) {
        OrderOutbox outbox = new OrderOutbox();
        outbox.setAggregateType(aggregateType);
        outbox.setAggregateId(aggregateId);
        outbox.setPayload(payload);
        outbox.setType(type);
        return outbox;
    }

}
