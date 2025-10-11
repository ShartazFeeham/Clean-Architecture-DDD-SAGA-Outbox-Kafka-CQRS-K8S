package cadsok.restaurant.domain.core.services;

import cadsok.restaurant.domain.core.entity.Payment;
import cadsok.restaurant.domain.core.event.PaymentCancelledEvent;
import cadsok.restaurant.domain.core.event.PaymentCompleteEvent;
import cadsok.restaurant.domain.core.event.PaymentInfoVerificationEvent;

public interface PaymentDomainService {

    PaymentInfoVerificationEvent verifyPaymentInfo(Payment payment);

    PaymentCompleteEvent pay(Payment payment);

    PaymentCancelledEvent cancel(Payment payment);

}
