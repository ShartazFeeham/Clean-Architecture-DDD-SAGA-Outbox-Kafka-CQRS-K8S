package cadsok.payment.domain.application.services;

import cadsok.payment.domain.application.ports.output.event.PaymentInitializedMessagePublisher;
import cadsok.payment.domain.core.event.PaymentInitializedEvent;
import commonmodule.infra.logging.LogAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentInitializedEventApplicationListener {

    private final PaymentInitializedMessagePublisher paymentInitializedMessagePublisher;

    @TransactionalEventListener
    @LogAction("Sending payment initialized event to message broker.")
    void process(PaymentInitializedEvent paymentInitializedEvent) {
        paymentInitializedMessagePublisher.publish(paymentInitializedEvent);
    }
}

