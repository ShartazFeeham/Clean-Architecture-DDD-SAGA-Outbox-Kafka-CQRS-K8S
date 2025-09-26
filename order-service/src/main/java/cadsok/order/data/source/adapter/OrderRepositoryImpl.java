package cadsok.order.data.source.adapter;

import cadsok.order.application.service.ports.output.repository.OrderRepository;
import cadsok.order.data.source.entities.OrderEntity;
import cadsok.order.data.source.mapper.OrderMapper;
import cadsok.order.data.source.repositories.OrderJpaRepository;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.values.TrackingId;
import commonmodule.domain.values.OrderId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = OrderMapper.toOrderEntity(order);
        orderJpaRepository.save(orderEntity);
        return OrderMapper.toOrder(orderEntity);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        Optional<OrderEntity> orderEntityOp = orderJpaRepository.findById(orderId.getValue());
        if (orderEntityOp.isEmpty()) {
            return Optional.empty();
        }
        return orderEntityOp.map(OrderMapper::toOrder);
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        Optional<OrderEntity> orderOp = orderJpaRepository.findByTrackingId(trackingId.getValue());
        if (orderOp.isEmpty()) {
            return Optional.empty();
        }
        return orderOp.map(OrderMapper::toOrder);
    }
}
