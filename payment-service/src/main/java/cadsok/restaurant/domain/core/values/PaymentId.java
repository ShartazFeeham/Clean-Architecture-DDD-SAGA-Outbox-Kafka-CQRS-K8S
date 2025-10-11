package cadsok.restaurant.domain.core.values;

import commonmodule.domain.values.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}
