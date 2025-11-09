package cadsok.restaurant.messaging.exception;

import commonmodule.infra.exception.EventException;

public class RestaurantEventException extends EventException {
    public RestaurantEventException(String message) {
        super(message);
    }
    public RestaurantEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
