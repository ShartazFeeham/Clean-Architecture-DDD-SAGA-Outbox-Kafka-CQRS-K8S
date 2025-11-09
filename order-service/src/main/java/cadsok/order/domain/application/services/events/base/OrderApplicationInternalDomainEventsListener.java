package cadsok.order.domain.application.services.events.base;

import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCancelledMessagePublisher;
import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCreatedMessagePublisher;
import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderPaymentValidatedMessagePublisher;
import cadsok.order.domain.core.event.*;
import cadsok.order.messaging.service.OrderCompletedEventPublisher;
import cadsok.order.messaging.service.OrderPaidEventPublisher;
import commonmodule.infra.logging.LogAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderApplicationInternalDomainEventsListener {

    private final OrderCreatedMessagePublisher orderCreatedMessagePublisher;
    private final OrderCancelledMessagePublisher orderCancelledMessagePublisher;
    private final OrderPaymentValidatedMessagePublisher orderPaymentValidatedMessagePublisher;
    private final OrderPaidEventPublisher orderPaidEventPublisher;
    private final OrderCompletedEventPublisher orderCompletedEventPublisher;

    @TransactionalEventListener
    @LogAction("Sending order created event to message broker")
    void process(OrderCreatedEvent event) {
        orderCreatedMessagePublisher.publish(event);
    }

    @TransactionalEventListener
    @LogAction("Sending order cancelled event to message broker")
    void process(OrderCancelledEvent event) {
        orderCancelledMessagePublisher.publish(event);
    }

    @TransactionalEventListener
    @LogAction("Sending order completed event to message broker")
    void process(OrderCompletedEvent event) {
        orderCompletedEventPublisher.publish(event);
    }

    @TransactionalEventListener
    @LogAction("Sending order cancelled event to message broker")
    void process(OrderPaymentValidEvent event) {
        orderPaymentValidatedMessagePublisher.publish(event);
    }

    @TransactionalEventListener
    @LogAction("Sending order paid event to message broker")
    void process(OrderPaidEvent event) {
        orderPaidEventPublisher.publish(event);
    }
}
