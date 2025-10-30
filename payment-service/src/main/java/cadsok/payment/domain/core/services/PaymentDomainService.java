package cadsok.payment.domain.core.services;

import cadsok.payment.domain.core.entity.Payment;
import cadsok.payment.domain.core.event.*;

public interface PaymentDomainService {

    PaymentInitializedEvent initializePayment(Payment payment); // Used from client input port

    PaymentProcessingEvent verifyAndProcessEvent(Payment payment); // Used from event input port

    PaymentCompleteEvent completePayment(Payment payment); // Used from event input port

    PaymentFailedEvent failedPayment(Payment payment); // Used from event input port

    PaymentCancelledEvent cancel(Payment payment); // Used from event input port

}
