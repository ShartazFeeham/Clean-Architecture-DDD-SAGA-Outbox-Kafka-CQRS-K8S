package cadsok.payment.domain.core.exception;

import commonmodule.domain.exceptions.DomainException;

public class PaymentNotFoundException extends DomainException {
    public PaymentNotFoundException(String message) {
        super(message);
    }
    public PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
