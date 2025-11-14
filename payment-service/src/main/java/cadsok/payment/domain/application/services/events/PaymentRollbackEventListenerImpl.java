package cadsok.payment.domain.application.services.events;

import cadsok.payment.domain.application.ports.input.event.PaymentRollbackEventListener;
import cadsok.payment.domain.application.ports.output.repository.PaymentRepository;
import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.PaymentRollbackFailedEvent;
import cadsok.payment.domain.core.event.PaymentRollbackSucceedEvent;
import cadsok.payment.domain.core.exception.PaymentNotFoundException;
import cadsok.payment.domain.core.values.PaymentId;
import cadsok.payment.gateway.PaymentGatewaySimulator;
import cadsok.payment.messaging.outbox.OutboxService;
import commonmodule.domain.values.DateTimeUtil;
import commonmodule.infra.logging.LogAction;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentRollbackEventListenerImpl implements PaymentRollbackEventListener {

    private final OutboxService outboxService;
    private final PaymentGatewaySimulator paymentGatewaySimulator;
    private final PaymentRepository paymentRepository;

    @Value("${kafka.topic-names.payment-rollback-success}")
    private String paymentRollbackSucceedTopicName;

    @Value("${kafka.topic-names.payment-rollback-failed}")
    private String paymentRollbackFailedTopicName;

    @Override
    @Transactional
    @LogAction(value = "Handling payment rollback event.")
    public void rollbackPayment(String paymentId) {
        Payment payment = getPaymentIfExist(paymentId);
        boolean rolledBack = paymentGatewaySimulator.rollbackPayment();

        if (rolledBack) {
            PaymentRollbackSucceedEvent event = new PaymentRollbackSucceedEvent(payment, DateTimeUtil.now());
            outboxService.handle(event, paymentRollbackSucceedTopicName);
        } else {
            PaymentRollbackFailedEvent event = new PaymentRollbackFailedEvent(payment, DateTimeUtil.now());
            outboxService.handle(event, paymentRollbackFailedTopicName);
        }

    }

    private Payment getPaymentIfExist(String paymentId) {
        if (Strings.isEmpty(paymentId)) {
            throw new PaymentNotFoundException("Invalid payment ID!");
        }
        Optional<Payment> paymentOp = paymentRepository.getPaymentById(new PaymentId(UUID.fromString(paymentId)));
        if (paymentOp.isEmpty()) {
            throw new PaymentNotFoundException("Payment with id " + paymentId + " doesn't exist.");
        }
        return paymentOp.get();
    }
}
