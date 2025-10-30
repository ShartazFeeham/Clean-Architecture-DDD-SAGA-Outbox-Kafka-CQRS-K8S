package cadsok.payment.domain.application.services.events;

import cadsok.payment.domain.application.models.PaymentCancelRequestDto;
import cadsok.payment.domain.application.ports.input.event.PaymentCancelEventListener;
import cadsok.payment.domain.application.ports.output.repository.PaymentRepository;
import cadsok.payment.domain.application.services.ApplicationDomainEventPublisher;
import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.PaymentCancelledEvent;
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
public class PaymentCancelEventListenerImpl implements PaymentCancelEventListener {

    private final PaymentRepository paymentRepository;
    private final PaymentDomainService paymentDomainService;
    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;

    @Override
    @LogAction(value = "Handling payment cancel event.")
    public void handlePaymentCancelEvent(PaymentCancelRequestDto paymentCancelRequestDto) {
        String paymentIdStr = paymentCancelRequestDto.paymentId();
        Payment payment = getPaymentIfExist(paymentIdStr);
        PaymentCancelledEvent event = paymentDomainService.cancel(payment);
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
