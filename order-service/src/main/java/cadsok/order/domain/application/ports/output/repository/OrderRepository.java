package cadsok.order.domain.application.ports.output.repository;

import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.values.TrackingId;
import commonmodule.domain.values.OrderId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);

}
