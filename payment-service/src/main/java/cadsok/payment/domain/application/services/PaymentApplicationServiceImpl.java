package cadsok.payment.domain.application.services;

import cadsok.payment.domain.application.mapper.PaymentMapper;
import cadsok.payment.domain.application.models.PaymentCreateRequestDto;
import cadsok.payment.domain.application.models.PaymentTrackingResponseDto;
import cadsok.payment.domain.application.ports.input.client.PaymentApplicationService;
import cadsok.payment.domain.application.ports.output.repository.PaymentRepository;
import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.PaymentInitializedEvent;
import cadsok.payment.domain.core.exception.PaymentDomainException;
import cadsok.payment.domain.core.exception.PaymentNotFoundException;
import cadsok.payment.domain.core.services.PaymentDomainService;
import cadsok.payment.domain.core.values.PaymentId;
import cadsok.payment.messaging.outbox.OutboxService;
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
public class PaymentApplicationServiceImpl implements PaymentApplicationService {

    private final OutboxService outboxService;
    private final PaymentRepository paymentRepository;
    private final PaymentDomainService paymentDomainService;

    @Value("${kafka.topic-names.payment-initialized}")
    private String paymentInitializedEventTopicName;

    @Override
    public PaymentTrackingResponseDto track(String paymentId) {
        return PaymentMapper.toResponse(getPaymentIfExist(paymentId));
    }

    @Override
    @Transactional
    @LogAction("Initializing payment.")
    public PaymentTrackingResponseDto initializePayment(PaymentCreateRequestDto paymentCreateRequestDto) {
        Payment payment = PaymentMapper.toPayment(paymentCreateRequestDto);
        validateIfAlreadyAPaymentActiveForSameOrder(payment);
        PaymentInitializedEvent event = paymentDomainService.initializePayment(payment);
        payment = paymentRepository.savePayment(payment);
        outboxService.handle(event, paymentInitializedEventTopicName);
        return PaymentMapper.toResponse(payment);
    }

    private void validateIfAlreadyAPaymentActiveForSameOrder(Payment payment) {
        if(paymentRepository.getPaymentByOrderId(payment.getOrderId()).isPresent()) {
             throw new PaymentDomainException("There is already a payment existing for the same order");
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
