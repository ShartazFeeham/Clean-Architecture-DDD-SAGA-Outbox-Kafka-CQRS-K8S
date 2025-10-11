package cadsok.restaurant.domain.core.services;

import cadsok.restaurant.domain.core.entity.Payment;
import cadsok.restaurant.domain.core.event.PaymentCancelledEvent;
import cadsok.restaurant.domain.core.event.PaymentCompleteEvent;
import cadsok.restaurant.domain.core.event.PaymentInfoVerificationEvent;
import commonmodule.domain.values.DateTimeUtil;
import org.springframework.stereotype.Service;

@Service
public class PaymentDomainServiceImpl implements PaymentDomainService {

    @Override
    public PaymentInfoVerificationEvent verifyPaymentInfo(Payment payment) {
        payment.validate();
        payment.initialize();
        return new PaymentInfoVerificationEvent(payment, DateTimeUtil.now());
    }

    @Override
    public PaymentCompleteEvent pay(Payment payment) {
        payment.pay();
        return new PaymentCompleteEvent(payment, DateTimeUtil.now());
    }

    @Override
    public PaymentCancelledEvent cancel(Payment payment) {
        payment.cancel();
        return new PaymentCancelledEvent(payment, DateTimeUtil.now());
    }
}
