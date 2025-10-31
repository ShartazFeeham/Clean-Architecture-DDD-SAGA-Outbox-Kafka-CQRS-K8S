package cadsok.order.domain.application.services.events;

import cadsok.order.domain.application.models.message.RestaurantApprovalResponse;
import cadsok.order.domain.application.ports.input.message.listener.restaurant.approval.RestaurantApprovalResponseMessageListener;
import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCancelledMessagePublisher;
import cadsok.order.domain.application.ports.output.repository.OrderRepository;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.event.OrderCancelledEvent;
import cadsok.order.domain.core.exception.OrderNotFoundException;
import cadsok.order.domain.core.services.OrderDomainService;
import commonmodule.domain.values.OrderId;
import commonmodule.infra.logging.LogAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

@Validated
@Service
@Slf4j
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

    private final OrderRepository orderRepository;
    private final OrderCancelledMessagePublisher orderCancelledMessagePublisher;
    private final OrderDomainService orderDomainService;

    public RestaurantApprovalResponseMessageListenerImpl
            (OrderRepository orderRepository,
             OrderCancelledMessagePublisher orderCancelledMessagePublisher,
             OrderDomainService orderDomainService) {
        this.orderRepository = orderRepository;
        this.orderCancelledMessagePublisher = orderCancelledMessagePublisher;
        this.orderDomainService = orderDomainService;
    }

    @Override
    @Transactional
    @LogAction(value = "Processing restaurant approval", identifiers = {"orderId"})
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        Order order = getOrder(restaurantApprovalResponse);
        orderDomainService.approveOrder(order);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    @LogAction(value = "Processing restaurant rejection", identifiers = {"orderId"})
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {
        Order order = getOrder(restaurantApprovalResponse);
        OrderCancelledEvent orderCancelledEvent = orderDomainService
                .cancelOrderPayment(order, restaurantApprovalResponse.getFailureMessages());
        orderRepository.save(order);
        orderCancelledMessagePublisher.publish(orderCancelledEvent);
    }

    private Order getOrder(RestaurantApprovalResponse restaurantApprovalResponse) {
        String orderIdStr = restaurantApprovalResponse.getOrderId();
        UUID orderIdUUID = UUID.fromString(restaurantApprovalResponse.getOrderId());
        Optional<Order> orderOp = orderRepository.findById(new OrderId(orderIdUUID));
        if (orderOp.isEmpty()) {
            throw new OrderNotFoundException("Order with id " + orderIdStr + " not found");
        }
        return orderOp.get();
    }
}
