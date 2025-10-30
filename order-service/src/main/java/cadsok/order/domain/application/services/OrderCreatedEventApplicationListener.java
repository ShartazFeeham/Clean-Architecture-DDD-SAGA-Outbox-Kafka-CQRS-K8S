package cadsok.order.domain.application.services;

import cadsok.order.domain.application.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import cadsok.order.domain.core.event.OrderCreatedEvent;
import commonmodule.infra.logging.LogAction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderCreatedEventApplicationListener {

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public OrderCreatedEventApplicationListener(OrderCreatedPaymentRequestMessagePublisher
                                                        orderCreatedPaymentRequestMessagePublisher) {
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    }

    @TransactionalEventListener
    @LogAction("Sending order created event to payment service via Kafka")
    void process(OrderCreatedEvent orderCreatedEvent) {
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
    }
}
