package cadsok.restaurant.messaging.exception;

import commonmodule.infra.exception.EventException;

public class OrderEventException extends EventException {
    public OrderEventException(String message) {
        super(message);
    }

    public OrderEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
