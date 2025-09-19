package cadsok.order.application.service.ports.output.repository;

import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.values.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);

}
