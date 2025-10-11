package cadsok.restaurant.domain.application.ports.output.repository;

import cadsok.restaurant.domain.core.entity.Order;
import cadsok.restaurant.domain.core.values.TrackingId;
import commonmodule.domain.values.OrderId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);

}
