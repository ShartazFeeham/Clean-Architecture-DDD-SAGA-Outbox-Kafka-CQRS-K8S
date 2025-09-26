package cadsok.order.domain.application.services;

import cadsok.order.domain.application.models.message.RestaurantApprovalResponse;
import cadsok.order.domain.application.ports.input.message.listener.restaurant.approval.RestaurantApprovalResponseMessageListener;
import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import cadsok.order.domain.application.ports.output.repository.OrderRepository;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.event.OrderCancelledEvent;
import cadsok.order.domain.core.exception.OrderNotFoundException;
import cadsok.order.domain.core.services.OrderDomainService;
import commonmodule.domain.values.OrderId;
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
    private final OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher;
    private final OrderDomainService orderDomainService;

    public RestaurantApprovalResponseMessageListenerImpl
            (OrderRepository orderRepository,
             OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher,
             OrderDomainService orderDomainService) {
        this.orderRepository = orderRepository;
        this.orderCancelledPaymentRequestMessagePublisher = orderCancelledPaymentRequestMessagePublisher;
        this.orderDomainService = orderDomainService;
    }

    @Override
    @Transactional
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        Order order = getOrder(restaurantApprovalResponse);
        orderDomainService.approveOrder(order);
        orderRepository.save(order);
        log.info("Order with id {} approved", order);
    }

    @Override
    @Transactional
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {
        Order order = getOrder(restaurantApprovalResponse);
        OrderCancelledEvent orderCancelledEvent = orderDomainService
                .cancelOrderPayment(order, restaurantApprovalResponse.getFailureMessages());
        orderRepository.save(order);
        log.info("Order with id {} is in cancelling state.", order);
        orderCancelledPaymentRequestMessagePublisher.publish(orderCancelledEvent);
    }

    private Order getOrder(RestaurantApprovalResponse restaurantApprovalResponse) {
        String orderIdStr = restaurantApprovalResponse.getOrderId();
        UUID orderIdUUID = UUID.fromString(restaurantApprovalResponse.getOrderId());
        log.info("Received order approved message for order id: {}", orderIdUUID);
        Optional<Order> orderOp = orderRepository.findById(new OrderId(orderIdUUID));
        if (orderOp.isEmpty()) {
            log.error("Order with id {} not found", orderIdStr);
            throw new OrderNotFoundException("Order with id " + orderIdStr + " not found");
        }
        return orderOp.get();
    }
}
