package cadsok.payment.data.service;

import cadsok.payment.data.entity.PaymentEntity;
import cadsok.payment.data.mapper.PaymentDbMapper;
import cadsok.payment.data.repository.PaymentJpaRepository;
import cadsok.payment.domain.application.ports.output.repository.PaymentRepository;
import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.values.PaymentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Optional<Payment> getPaymentById(PaymentId paymentId) {
        Optional<PaymentEntity> paymentEntityOp = paymentJpaRepository.findById(paymentId.getValue());
        return paymentEntityOp.map(PaymentDbMapper::toPayment);
    }

    @Override
    public Payment savePayment(Payment payment) {
        PaymentEntity paymentEntity = PaymentDbMapper.toEntity(payment);
        PaymentEntity result = paymentJpaRepository.save(paymentEntity);
        return PaymentDbMapper.toPayment(result);
    }
}
