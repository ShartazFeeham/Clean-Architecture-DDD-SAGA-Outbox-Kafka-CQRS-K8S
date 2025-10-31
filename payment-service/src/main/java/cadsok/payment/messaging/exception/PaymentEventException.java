package cadsok.payment.messaging.exception;

import commonmodule.infra.exception.EventException;

public class PaymentEventException extends EventException {
    public PaymentEventException(String message) {
        super(message);
    }
    public PaymentEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
