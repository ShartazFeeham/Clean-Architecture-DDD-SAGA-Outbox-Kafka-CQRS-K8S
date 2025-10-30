package cadsok.payment.domain.core.services;

import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.PaymentCancelledEvent;
import cadsok.payment.domain.core.event.PaymentCompleteEvent;
import cadsok.payment.domain.core.event.PaymentInfoVerificationEvent;

public interface PaymentDomainService {

    PaymentInfoVerificationEvent verifyPaymentInfo(Payment payment);

    PaymentCompleteEvent pay(Payment payment);

    PaymentCancelledEvent cancel(Payment payment);

}
