package cadsok.order.domain.core.values;

import commonmodule.domain.values.BaseId;

import java.util.UUID;

public class OrderItemId extends BaseId<UUID> {
    public OrderItemId(UUID value) {
        super(value);
    }
}
