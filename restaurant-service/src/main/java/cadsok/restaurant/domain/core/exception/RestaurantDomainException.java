package cadsok.restaurant.domain.core.exception;

import commonmodule.domain.exceptions.DomainException;

public class RestaurantDomainException extends DomainException {

    public RestaurantDomainException(String message) {
        super(message);
    }

    public RestaurantDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
