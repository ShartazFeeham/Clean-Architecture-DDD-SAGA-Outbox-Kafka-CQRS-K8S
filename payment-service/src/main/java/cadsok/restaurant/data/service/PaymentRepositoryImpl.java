package cadsok.restaurant.data.service;

import cadsok.restaurant.data.entity.PaymentEntity;
import cadsok.restaurant.data.mapper.PaymentDbMapper;
import cadsok.restaurant.data.repository.PaymentJpaRepository;
import cadsok.restaurant.domain.application.ports.output.repository.PaymentRepository;
import cadsok.restaurant.domain.core.entity.Payment;
import cadsok.restaurant.domain.core.values.PaymentId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentRepositoryImpl(PaymentJpaRepository paymentJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
    }

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
