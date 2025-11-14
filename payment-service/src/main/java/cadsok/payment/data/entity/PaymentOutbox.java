package cadsok.payment.data.entity;

import commonmodule.messaging.OutboxBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "outbox")
@NoArgsConstructor
public class PaymentOutbox extends OutboxBaseEntity {

    public static PaymentOutbox create(String payload, String aggregateType, String aggregateId, String type) {
        PaymentOutbox outbox = new PaymentOutbox();
        outbox.setAggregateType(aggregateType);
        outbox.setAggregateId(aggregateId);
        outbox.setPayload(payload);
        outbox.setType(type);
        return outbox;
    }

}
