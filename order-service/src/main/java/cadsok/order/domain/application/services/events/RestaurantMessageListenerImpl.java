package cadsok.order.domain.application.services.events;

import cadsok.order.domain.application.ports.input.message.listener.restaurant.RestaurantMessageListener;
import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCancelledMessagePublisher;
import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCompletedMessagePublisher;
import cadsok.order.domain.application.ports.output.repository.OrderRepository;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.event.OrderCancelledEvent;
import cadsok.order.domain.core.event.OrderCompletedEvent;
import cadsok.order.domain.core.exception.OrderDomainException;
import cadsok.order.domain.core.exception.OrderNotFoundException;
import cadsok.order.domain.core.services.OrderDomainService;
import cadsok.order.domain.core.values.TrackingId;
import commonmodule.domain.values.OrderId;
import commonmodule.domain.values.OrderStatus;
import commonmodule.infra.logging.LogAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@Service
@Slf4j
public class RestaurantMessageListenerImpl implements RestaurantMessageListener {

    private final OrderRepository orderRepository;
    private final OrderCancelledMessagePublisher orderCancelledMessagePublisher;
    private final OrderCompletedMessagePublisher orderCompletedMessagePublisher;
    private final OrderDomainService orderDomainService;

    public RestaurantMessageListenerImpl
            (OrderRepository orderRepository,
             OrderCancelledMessagePublisher orderCancelledMessagePublisher, OrderCompletedMessagePublisher orderCompletedMessagePublisher,
             OrderDomainService orderDomainService) {
        this.orderRepository = orderRepository;
        this.orderCancelledMessagePublisher = orderCancelledMessagePublisher;
        this.orderCompletedMessagePublisher = orderCompletedMessagePublisher;
        this.orderDomainService = orderDomainService;
    }

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
        orderCompletedMessagePublisher.publish(event);
    }

    @LogAction(value = "Processing restaurant rejection", identifiers = {"orderId"})
    public void orderRejected(String orderId, String message) {
        Order order = getOrder(orderId);
        validateIfOrderIsAlreadyCompletedOrCancelled(order);
        OrderCancelledEvent orderCancelledEvent = orderDomainService
                .cancelOrder(order, new ArrayList<>(List.of(message)));
        orderRepository.updateStatus(new OrderId(UUID.fromString(orderId)), OrderStatus.CANCELLED);
        orderCancelledMessagePublisher.publish(orderCancelledEvent);
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
