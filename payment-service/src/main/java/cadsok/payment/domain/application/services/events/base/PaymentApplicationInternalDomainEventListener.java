package cadsok.payment.domain.application.services.events.base;

import cadsok.payment.domain.application.ports.output.event.*;
import cadsok.payment.domain.core.event.*;
import commonmodule.infra.logging.LogAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentApplicationInternalDomainEventListener {

    private final PaymentCancelMessagePublisher paymentCancelMessagePublisher;
    private final PaymentCompleteMessagePublisher paymentCompleteMessagePublisher;
    private final PaymentIFailedMessagePublisher paymentIFailedMessagePublisher;
    private final PaymentInitializedMessagePublisher paymentInitializedMessagePublisher;
    private final PaymentProcessingMessagePublisher paymentProcessingMessagePublisher;
    private final PaymentRollbackSuccessMessagePublisher paymentRollbackSuccessMessagePublisher;
    private final PaymentRollbackFailedMessagePublisher paymentRollbackFailedMessagePublisher;

    @TransactionalEventListener
    @LogAction("Sending payment cancelled event to message broker.")
    void process(PaymentCancelledEvent event) {
        paymentCancelMessagePublisher.publish(event);
    }

    @TransactionalEventListener
    @LogAction("Sending payment complete event to message broker.")
    void process(PaymentCompleteEvent event) {
        paymentCompleteMessagePublisher.publish(event);
    }

    @TransactionalEventListener
    @LogAction("Sending payment failed event to message broker.")
    void process(PaymentFailedEvent event) {
        paymentIFailedMessagePublisher.publish(event);
    }

    @TransactionalEventListener
    @LogAction("Sending payment initialized event to message broker.")
    void process(PaymentInitializedEvent event) {
        paymentInitializedMessagePublisher.publish(event);
    }

    @TransactionalEventListener
    @LogAction("Sending payment processing event to message broker.")
    void process(PaymentProcessingEvent event) {
        paymentProcessingMessagePublisher.publish(event);
    }

    @TransactionalEventListener
    @LogAction("Sending payment rollback success event to message broker.")
    void process(PaymentRollbackSucceedEvent event) {
        paymentRollbackSuccessMessagePublisher.publish(event);
    }

    @TransactionalEventListener
    @LogAction("Sending payment rollback failed event to message broker.")
    void process(PaymentRollbackFailedEvent event) {
        paymentRollbackFailedMessagePublisher.publish(event);
    }
}
