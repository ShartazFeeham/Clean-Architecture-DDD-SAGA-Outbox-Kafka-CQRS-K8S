package cadsok.payment.domain.application.services.events;

import cadsok.payment.domain.application.models.PaymentCancelRequestDto;
import cadsok.payment.domain.application.ports.input.event.PaymentCancelEventListener;
import cadsok.payment.domain.application.ports.output.repository.PaymentRepository;
import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.PaymentCancelledEvent;
import cadsok.payment.domain.core.exception.PaymentNotFoundException;
import cadsok.payment.domain.core.services.PaymentDomainService;
import cadsok.payment.messaging.outbox.OutboxService;
import commonmodule.domain.values.OrderId;
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
public class PaymentCancelEventListenerImpl implements PaymentCancelEventListener {

    private final OutboxService outboxService;
    private final PaymentRepository paymentRepository;
    private final PaymentDomainService paymentDomainService;

    @Value("${kafka.topic-names.payment-cancel}")
    private String paymentCancelledEventTopicName;

    @Override
    @Transactional
    @LogAction(value = "Handling payment cancel event.")
    public void handlePaymentCancelEvent(PaymentCancelRequestDto paymentCancelRequestDto) {
        String trackingId = paymentCancelRequestDto.orderTrackingId();
        Payment payment = getPaymentIfExist(trackingId);
        PaymentCancelledEvent event = paymentDomainService.cancel(payment);
        paymentRepository.savePayment(payment);
        outboxService.handle(event, paymentCancelledEventTopicName);
    }

    private Payment getPaymentIfExist(String trackingId) {
        if (Strings.isEmpty(trackingId)) {
            throw new PaymentNotFoundException("Invalid order tracking ID!");
        }
        Optional<Payment> paymentOp = paymentRepository.getPaymentByOrderId(new OrderId(UUID.fromString(trackingId)));
        if (paymentOp.isEmpty()) {
            throw new PaymentNotFoundException("Payment with order tracking id " + trackingId + " doesn't exist.");
        }
        return paymentOp.get();
    }
}
