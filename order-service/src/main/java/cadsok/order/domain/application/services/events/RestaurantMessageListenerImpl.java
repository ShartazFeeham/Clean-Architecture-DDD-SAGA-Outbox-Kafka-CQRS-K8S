package cadsok.order.domain.application.services.events;

import cadsok.order.domain.application.ports.input.message.listener.restaurant.RestaurantMessageListener;
import cadsok.order.domain.application.ports.output.repository.OrderRepository;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.event.OrderCancelledEvent;
import cadsok.order.domain.core.event.OrderCompletedEvent;
import cadsok.order.domain.core.exception.OrderDomainException;
import cadsok.order.domain.core.exception.OrderNotFoundException;
import cadsok.order.domain.core.services.OrderDomainService;
import cadsok.order.domain.core.values.TrackingId;
import cadsok.order.messaging.outbox.OutboxService;
import commonmodule.domain.values.OrderId;
import commonmodule.domain.values.OrderStatus;
import commonmodule.infra.logging.LogAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Validated
@Service
@Slf4j
public class RestaurantMessageListenerImpl implements RestaurantMessageListener {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OutboxService outboxService;

    @Value("${kafka.topic-names.order-completed}")
    private String orderCompletedEventTopicName;

    @Value("${kafka.topic-names.order-cancelled}")
    private String orderCancelledEventTopicName;

    @Override
    @Transactional
    public void orderApproved(String orderId, boolean approved, String message) {
        if (approved) {
            orderApproved(orderId);
        } else {
            orderRejected(orderId, message);
        }
    }

    @LogAction(value = "Processing restaurant approval", identifiers = {"orderId"})
    private void orderApproved(String orderId) {
        Order order = getOrder(orderId);
        OrderCompletedEvent event = orderDomainService.approveOrder(order);
        orderRepository.updateStatus(new OrderId(UUID.fromString(orderId)), OrderStatus.COMPLETED);
        outboxService.handle(event, orderCompletedEventTopicName);
    }

    @LogAction(value = "Processing restaurant rejection", identifiers = {"orderId"})
    public void orderRejected(String orderId, String message) {
        Order order = getOrder(orderId);
        validateIfOrderIsAlreadyCompletedOrCancelled(order);
        OrderCancelledEvent orderCancelledEvent = orderDomainService
                .cancelOrder(order, new ArrayList<>(List.of(message)));
        orderRepository.updateStatus(new OrderId(UUID.fromString(orderId)), OrderStatus.CANCELLED);
        outboxService.handle(orderCancelledEvent, orderCancelledEventTopicName);
    }

    private void validateIfOrderIsAlreadyCompletedOrCancelled(Order order) {
        if (order.getOrderStatus() == OrderStatus.COMPLETED || order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new OrderDomainException("Can't cancel an order that is already completed or cancelled");
        }
    }

    private Order getOrder(String orderId) {
        UUID orderIdUUID = UUID.fromString(orderId);
        Optional<Order> orderOp = orderRepository.findByTrackingId(new TrackingId(orderIdUUID));
        if (orderOp.isEmpty()) {
            throw new OrderNotFoundException("Order with id " + orderId + " not found");
        }
        return orderOp.get();
    }
}
