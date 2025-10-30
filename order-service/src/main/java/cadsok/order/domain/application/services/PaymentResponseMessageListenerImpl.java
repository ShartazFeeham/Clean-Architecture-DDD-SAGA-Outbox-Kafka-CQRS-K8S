package cadsok.order.domain.application.services;

import cadsok.order.domain.application.models.message.PaymentResponse;
import cadsok.order.domain.application.ports.input.message.listener.payment.PaymentResponseMessageListener;
import cadsok.order.domain.application.ports.output.message.publisher.restaurant.approval.OrderPaidRestaurantRequestMessagePublisher;
import cadsok.order.domain.application.ports.output.repository.OrderRepository;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.event.OrderPaidEvent;
import cadsok.order.domain.core.exception.OrderNotFoundException;
import cadsok.order.domain.core.services.OrderDomainService;
import commonmodule.domain.values.OrderId;
import commonmodule.infra.logging.Auditable;
import commonmodule.infra.logging.LogAction;
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
    @Auditable(action = "PAYMENT_COMPLETED", resource = "Payment")
    @LogAction(value = "Processing payment completion", identifiers = {"orderId"})
    public void paymentCompleted(PaymentResponse paymentResponse) {
        Order order = getOrder(paymentResponse);
        OrderPaidEvent orderPaidEvent = orderDomainService.payOrder(order);
        orderRepository.save(order);
        orderPaidRestaurantRequestMessagePublisher.publish(orderPaidEvent);
    }

    @Override
    @LogAction(value = "Processing payment cancellation", identifiers = {"orderId"})
    public void paymentCancelled(PaymentResponse paymentResponse) {
        Order order = getOrder(paymentResponse);
        orderDomainService.cancelOrder(order, paymentResponse.getFailureMessages());
        orderRepository.save(order);
    }

    private Order getOrder(PaymentResponse paymentResponse) {
        String orderIdStr = paymentResponse.getOrderId();
        UUID orderIdUUID = UUID.fromString(paymentResponse.getOrderId());
        Optional<Order> orderOp = orderRepository.findById(new OrderId(orderIdUUID));
        if (orderOp.isEmpty()) {
            throw new OrderNotFoundException("Order with id " + orderIdStr + " not found");
        }
        return orderOp.get();
    }
}
