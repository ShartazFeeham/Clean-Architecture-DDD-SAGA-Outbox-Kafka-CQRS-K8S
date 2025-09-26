package cadsok.order.application.service.services;

import cadsok.order.application.service.models.message.PaymentResponse;
import cadsok.order.application.service.models.message.RestaurantApprovalResponse;
import cadsok.order.application.service.ports.input.message.listener.payment.PaymentResponseMessageListener;
import cadsok.order.application.service.ports.input.service.OrderApplicationService;
import cadsok.order.application.service.ports.output.message.publisher.restaurant.approval.OrderPaidRestaurantRequestMessagePublisher;
import cadsok.order.application.service.ports.output.repository.OrderRepository;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.event.OrderPaidEvent;
import cadsok.order.domain.core.exception.OrderNotFoundException;
import cadsok.order.domain.core.services.OrderDomainService;
import commonmodule.domain.values.OrderId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

@Validated
@Service
@Slf4j
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher;

    public PaymentResponseMessageListenerImpl(OrderRepository orderRepository,
                                              OrderDomainService orderDomainService,
                                              OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher) {
        this.orderRepository = orderRepository;
        this.orderDomainService = orderDomainService;
        this.orderPaidRestaurantRequestMessagePublisher = orderPaidRestaurantRequestMessagePublisher;
    }

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {
        Order order = getOrder(paymentResponse);
        OrderPaidEvent orderPaidEvent = orderDomainService.payOrder(order);
        orderRepository.save(order);
        log.info("Order with id {} has been paid", order.getId().getValue());
        orderPaidRestaurantRequestMessagePublisher.publish(orderPaidEvent);
    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {
        Order order = getOrder(paymentResponse);
        orderDomainService.cancelOrder(order, paymentResponse.getFailureMessages());
        orderRepository.save(order);
        log.info("Order with id {} has been cancelled", order.getId().getValue());
    }

    private Order getOrder(PaymentResponse paymentResponse) {
        String orderIdStr = paymentResponse.getOrderId();
        UUID orderIdUUID = UUID.fromString(paymentResponse.getOrderId());
        log.info("Received order approved message for order id: {}", orderIdUUID);
        Optional<Order> orderOp = orderRepository.findById(new OrderId(orderIdUUID));
        if (orderOp.isEmpty()) {
            log.error("Order with id {} not found", orderIdStr);
            throw new OrderNotFoundException("Order with id " + orderIdStr + " not found");
        }
        return orderOp.get();
    }
}
