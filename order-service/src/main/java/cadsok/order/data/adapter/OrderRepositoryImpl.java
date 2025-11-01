package cadsok.order.data.adapter;

import cadsok.order.data.entities.StreetAddressEntity;
import cadsok.order.data.mapper.DeliveryAddressMapper;
import cadsok.order.data.mapper.OrderStatusMapper;
import cadsok.order.data.repositories.StreetAddressJpaRepository;
import cadsok.order.domain.application.ports.output.repository.OrderRepository;
import cadsok.order.data.entities.OrderEntity;
import cadsok.order.data.mapper.OrderMapper;
import cadsok.order.data.repositories.OrderJpaRepository;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.values.TrackingId;
import commonmodule.domain.values.OrderId;
import commonmodule.domain.values.OrderStatus;
import commonmodule.infra.logging.LogAction;
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
    @LogAction(value = "Saving order in database", identifiers = {"orderId"})
    public Order save(Order order) {
        StreetAddressEntity deliveryAddress = DeliveryAddressMapper.toEntity(order.getDeliveryAddress());
        StreetAddressEntity savedAddress = streetAddressJpaRepository.save(deliveryAddress);
        OrderEntity orderEntity = OrderMapper.toOrderEntity(order, savedAddress);
        orderJpaRepository.save(orderEntity);
        return OrderMapper.toOrder(orderEntity);
    }

    @Override
    public void updateStatus(OrderId orderId, OrderStatus orderStatus) {
        Optional<OrderEntity> orderEntityOp = orderJpaRepository.findById(orderId.getValue());
        orderEntityOp.ifPresent(orderEntity ->{
            orderEntity.setOrderStatus(OrderStatusMapper.toDbStatus(orderStatus));
            orderJpaRepository.save(orderEntity);
        });
        orderEntityOp.map(OrderMapper::toOrder);
    }

    @Override
    @LogAction(value = "Finding order by id in database", identifiers = {"orderId"})
    public Optional<Order> findById(OrderId orderId) {
        Optional<OrderEntity> orderEntityOp = orderJpaRepository.findById(orderId.getValue());
        if (orderEntityOp.isEmpty()) {
            return Optional.empty();
        }
        return orderEntityOp.map(OrderMapper::toOrder);
    }

    @Override
    @LogAction(value = "Finding order by tracking id in database", identifiers = {"trackingId"})
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        Optional<OrderEntity> orderOp = orderJpaRepository.findByTrackingId(trackingId.getValue());
        if (orderOp.isEmpty()) {
            return Optional.empty();
        }
        return orderOp.map(OrderMapper::toOrder);
    }
}
