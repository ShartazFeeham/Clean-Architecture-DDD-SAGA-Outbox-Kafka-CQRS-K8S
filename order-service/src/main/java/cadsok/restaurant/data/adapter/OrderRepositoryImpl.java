package cadsok.restaurant.data.adapter;

import cadsok.restaurant.data.entities.StreetAddressEntity;
import cadsok.restaurant.data.mapper.DeliveryAddressMapper;
import cadsok.restaurant.data.repositories.StreetAddressJpaRepository;
import cadsok.restaurant.domain.application.ports.output.repository.OrderRepository;
import cadsok.restaurant.data.entities.OrderEntity;
import cadsok.restaurant.data.mapper.OrderMapper;
import cadsok.restaurant.data.repositories.OrderJpaRepository;
import cadsok.restaurant.domain.core.entity.Order;
import cadsok.restaurant.domain.core.values.TrackingId;
import commonmodule.domain.values.OrderId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final StreetAddressJpaRepository streetAddressJpaRepository;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository, StreetAddressJpaRepository streetAddressJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.streetAddressJpaRepository = streetAddressJpaRepository;
    }

    @Override
    public Order save(Order order) {
        StreetAddressEntity deliveryAddress = DeliveryAddressMapper.toEntity(order.getDeliveryAddress());
        StreetAddressEntity savedAddress = streetAddressJpaRepository.save(deliveryAddress);
        OrderEntity orderEntity = OrderMapper.toOrderEntity(order, savedAddress);
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
