package cadsok.restaurant.domain.application.services;

import cadsok.restaurant.domain.application.mapper.OrderDataMapper;
import cadsok.restaurant.domain.application.models.track.TrackOrderQuery;
import cadsok.restaurant.domain.application.models.track.TrackOrderResponse;
import cadsok.restaurant.domain.application.ports.output.repository.OrderRepository;
import cadsok.restaurant.domain.core.entity.Order;
import cadsok.restaurant.domain.core.exception.OrderNotFoundException;
import cadsok.restaurant.domain.core.values.TrackingId;
import commonmodule.infra.logging.LogAction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class OrderTrackingQueryHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    public OrderTrackingQueryHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    @LogAction(value = "Tracking order status", identifiers = {"trackingId"})
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult =
                orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if (orderResult.isEmpty()) {
            throw new OrderNotFoundException("Could not find order with tracking id: " +
                    trackOrderQuery.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
