package cadsok.restaurant.domain.core.values;

import commonmodule.domain.values.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
