package cadsok.payment.domain.core.services;

import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.*;

public interface PaymentDomainService {

    PaymentInfoInitializedEvent initializePayment(Payment payment);

    PaymentProcessingEvent verifyAndProcessEvent(Payment payment);

    PaymentCompleteEvent completePayment(Payment payment);

    PaymentFailedEvent failedPayment(Payment payment);

    PaymentCancelledEvent cancel(Payment payment);

}
