package cadsok.payment.domain.application.services.events;

import cadsok.payment.domain.application.models.PaymentGatewayRequestDto;
import cadsok.payment.domain.application.ports.input.event.PaymentGatewayMessageListener;
import cadsok.payment.domain.application.ports.output.repository.PaymentRepository;
import cadsok.payment.domain.application.services.events.base.PaymentApplicationInternalDomainEventPublisher;
import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.PaymentEvent;
import cadsok.payment.domain.core.exception.PaymentNotFoundException;
import cadsok.payment.domain.core.services.PaymentDomainService;
import cadsok.payment.domain.core.values.PaymentId;
import commonmodule.domain.values.PaymentStatus;
import commonmodule.infra.logging.LogAction;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentGatewayMessageListenerImpl implements PaymentGatewayMessageListener {

    private final PaymentRepository paymentRepository;
    private final PaymentDomainService paymentDomainService;
    private final PaymentApplicationInternalDomainEventPublisher paymentApplicationInternalDomainEventPublisher;

    @Override
    @Transactional
    @LogAction(value = "Handling payment-gateway response event.")
    public void handlePaymentGatewayResponse(PaymentGatewayRequestDto paymentCancelRequestDto) {
        String paymentIdStr = paymentCancelRequestDto.paymentId();
        Payment payment = getPaymentIfExist(paymentIdStr);

        PaymentEvent event;
        if (paymentCancelRequestDto.paymentStatus() == PaymentStatus.COMPLETED) {
            event = paymentDomainService.completePayment(payment);
        } else {
            event = paymentDomainService.failedPayment(payment);
        }

        paymentApplicationInternalDomainEventPublisher.publish(event);
        paymentRepository.savePayment(payment);
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