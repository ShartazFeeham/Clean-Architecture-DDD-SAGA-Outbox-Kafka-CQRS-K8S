package cadsok.order.domain.core.values;

import cadsok.common.domain.values.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
