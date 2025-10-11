package cadsok.restaurant.domain.application.services;

import cadsok.restaurant.domain.application.mapper.PaymentMapper;
import cadsok.restaurant.domain.application.models.PaymentCancelRequestDto;
import cadsok.restaurant.domain.application.models.PaymentCreateRequestDto;
import cadsok.restaurant.domain.application.models.PaymentResponseDto;
import cadsok.restaurant.domain.application.ports.input.client.PaymentApplicationService;
import cadsok.restaurant.domain.application.ports.output.repository.PaymentRepository;
import cadsok.restaurant.domain.core.entity.Payment;
import cadsok.restaurant.domain.core.event.PaymentCancelledEvent;
import cadsok.restaurant.domain.core.event.PaymentCompleteEvent;
import cadsok.restaurant.domain.core.event.PaymentInfoVerificationEvent;
import cadsok.restaurant.domain.core.exception.PaymentDomainException;
import cadsok.restaurant.domain.core.exception.PaymentNotFoundException;
import cadsok.restaurant.domain.core.services.PaymentDomainService;
import cadsok.restaurant.domain.core.values.PaymentId;
import commonmodule.domain.values.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PaymentApplicationServiceImpl implements PaymentApplicationService {

    private final PaymentRepository paymentRepository;
    private final PaymentDomainService paymentDomainService;
    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;

    public PaymentApplicationServiceImpl(PaymentRepository paymentRepository,
                                         PaymentDomainService paymentDomainService,
                                         ApplicationDomainEventPublisher applicationDomainEventPublisher) {
        this.paymentRepository = paymentRepository;
        this.paymentDomainService = paymentDomainService;
        this.applicationDomainEventPublisher = applicationDomainEventPublisher;
    }

    @Override
    public PaymentResponseDto info(String paymentId) {
        return PaymentMapper.toResponse(getPaymentIfExist(paymentId));
    }

    @Override
    public PaymentResponseDto initPay(PaymentCreateRequestDto paymentCreateRequestDto) {
        Payment payment = PaymentMapper.toPayment(paymentCreateRequestDto);
        PaymentInfoVerificationEvent event = paymentDomainService.verifyPaymentInfo(payment);
        payment = paymentRepository.savePayment(payment);
        log.info("Payment is initialized and saved in database.");
        applicationDomainEventPublisher.publish(event);
        return PaymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponseDto completePay(String paymentId) {
        Payment payment = getPaymentIfExist(paymentId);
        PaymentCompleteEvent event = paymentDomainService.pay(payment);
        payment = paymentRepository.savePayment(payment);
        log.info("Payment completion is updated into database.");
        applicationDomainEventPublisher.publish(event);
        return PaymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponseDto cancel(PaymentCancelRequestDto paymentCancelRequestDto) {
        Payment payment = getPaymentIfExist(paymentCancelRequestDto.getPaymentId());
        if (payment.getPaymentStatus().equals(PaymentStatus.COMPLETED) || payment.getPaymentStatus().equals(PaymentStatus.PROCESSING)) {
            PaymentCancelledEvent event = paymentDomainService.cancel(payment);
            PaymentResponseDto response = PaymentMapper.toResponse(paymentRepository.savePayment(payment));
            log.info("Payment is cancelled in repo.");
            applicationDomainEventPublisher.publish(event);
            return response;
        }

        log.error("Payment is in incorrect step to be cancelled.");
        throw new PaymentDomainException("Payment is in incorrect step to be cancelled. Current state: "
                + payment.getPaymentStatus() + ". Allowed status's to perform cancel: "
                + PaymentStatus.COMPLETED + " & " + PaymentStatus.PROCESSING);
    }

    private Payment getPaymentIfExist(String paymentId) {
        if (Strings.isEmpty(paymentId)) {
            log.error("Invalid paymentId.");
            throw new PaymentNotFoundException("Invalid payment ID!");
        }
        Optional<Payment> paymentOp = paymentRepository.getPaymentById(new PaymentId(UUID.fromString(paymentId)));
        if (paymentOp.isEmpty()) {
            log.error("Payment is not found!");
            throw new PaymentNotFoundException("Payment with id " + paymentId + " doesn't exist.");
        }
        log.info("Payment found: {}", paymentOp);
        return paymentOp.get();
    }

}
