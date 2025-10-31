package cadsok.order.domain.application.services.events;

import cadsok.order.domain.application.models.message.PaymentResponse;
import cadsok.order.domain.application.ports.input.message.listener.payment.PaymentResponseMessageListener;
import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderPaymentValidatedMessagePublisher;
import cadsok.order.domain.application.ports.output.repository.OrderRepository;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.event.OrderPaymentValidEvent;
import cadsok.order.domain.core.exception.OrderNotFoundException;
import cadsok.order.domain.core.services.OrderDomainService;
import cadsok.order.domain.core.values.TrackingId;
import commonmodule.domain.values.OrderStatus;
import commonmodule.infra.logging.Auditable;
import commonmodule.infra.logging.LogAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OrderPaymentValidatedMessagePublisher orderPaymentValidatedMessagePublisher;

    public PaymentResponseMessageListenerImpl(OrderRepository orderRepository,
                                              OrderDomainService orderDomainService,
                                              OrderPaymentValidatedMessagePublisher orderPaymentValidatedMessagePublisher) {
        this.orderRepository = orderRepository;
        this.orderDomainService = orderDomainService;
        this.orderPaymentValidatedMessagePublisher = orderPaymentValidatedMessagePublisher;
    }

    @Override
    @Auditable(action = "PAYMENT_COMPLETED", resource = "Payment")
    @LogAction(value = "Processing payment completion", identifiers = {"orderId"})
    @Transactional
    public void paymentValidation(String orderId, String price) {
        Order order = getOrder(orderId);
        orderRepository.updateStatus(order.getId(), OrderStatus.PAID);
        OrderPaymentValidEvent orderPaidEvent = orderDomainService.validateAndPayOrder(order);
        orderPaymentValidatedMessagePublisher.publish(orderPaidEvent);
    }

    @Override
    @LogAction(value = "Processing payment cancellation", identifiers = {"orderId"})
    public void paymentCancelled(PaymentResponse paymentResponse) {
        // TODO: Implement if needed
    }

    @Transactional(readOnly = true)
    @LogAction(value = "Searching for order with provided trackingId", identifiers = {"orderIdStr"})
    public Order getOrder(String orderIdStr) {
        UUID orderIdUUID = UUID.fromString(orderIdStr);
        return orderRepository.findByTrackingId(new TrackingId(orderIdUUID)).orElseThrow(
                () -> new OrderNotFoundException("Order with id " + orderIdStr + " not found")
        );
    }
}
