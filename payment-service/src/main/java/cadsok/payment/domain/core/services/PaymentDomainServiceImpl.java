package cadsok.payment.domain.core.services;

import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.*;
import commonmodule.domain.values.DateTimeUtil;

public class PaymentDomainServiceImpl implements PaymentDomainService {

    @Override
    public PaymentInfoInitializedEvent initializePayment(Payment payment) {
        payment.validate();
        ;
        payment.initialize();
        return new PaymentInfoInitializedEvent(payment, DateTimeUtil.now());
    }

    @Override
    public PaymentProcessingEvent verifyAndProcessEvent(Payment payment) {
        payment.process();
        return new PaymentProcessingEvent(payment, DateTimeUtil.now());
    }

    @Override
    public PaymentCompleteEvent completePayment(Payment payment) {
        payment.pay();
        return new PaymentCompleteEvent(payment, DateTimeUtil.now());
    }

    @Override
    public PaymentFailedEvent failedPayment(Payment payment) {
        payment.fail();
        return new PaymentFailedEvent(payment, DateTimeUtil.now());
    }

    @Override
    public PaymentCancelledEvent cancel(Payment payment) {
        payment.cancel();
        return new PaymentCancelledEvent(payment, DateTimeUtil.now());
    }
}
