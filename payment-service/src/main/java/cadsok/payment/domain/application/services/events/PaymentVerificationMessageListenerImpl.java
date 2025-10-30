package cadsok.payment.domain.application.services.events;

import cadsok.payment.domain.application.models.PaymentInfoVarificationDto;
import cadsok.payment.domain.application.ports.input.event.PaymentVerificationMessageListener;
import cadsok.payment.domain.application.ports.output.repository.PaymentRepository;
import cadsok.payment.domain.application.services.ApplicationDomainEventPublisher;
import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.PaymentEvent;
import cadsok.payment.domain.core.exception.PaymentNotFoundException;
import cadsok.payment.domain.core.services.PaymentDomainService;
import cadsok.payment.domain.core.values.PaymentId;
import commonmodule.infra.logging.LogAction;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentVerificationMessageListenerImpl implements PaymentVerificationMessageListener {

    private final PaymentRepository paymentRepository;
    private final PaymentDomainService paymentDomainService;
    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;

    @Override
    @LogAction(value = "Handling payment-verification event.")
    public void handleVerificationResponse(PaymentInfoVarificationDto paymentInfoVarificationDto) {
        String paymentIdStr = paymentInfoVarificationDto.paymentId();
        Payment payment = getPaymentIfExist(paymentIdStr);

        PaymentEvent event;
        if (paymentInfoVarificationDto.isValid()) {
            event = paymentDomainService.verifyAndProcessEvent(payment);
        } else {
            event = paymentDomainService.failedPayment(payment);
        }

        applicationDomainEventPublisher.publish(event);
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