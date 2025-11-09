package cadsok.restaurant.application.exception;

import cadsok.restaurant.domain.core.exception.RestaurantDomainException;
import commonmodule.application.ErrorDTO;
import commonmodule.application.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class RestaurantGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {RestaurantDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(RestaurantDomainException restaurantException) {
        log.error(restaurantException.getMessage(), restaurantException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(restaurantException.getMessage())
                .build();
    }
}
