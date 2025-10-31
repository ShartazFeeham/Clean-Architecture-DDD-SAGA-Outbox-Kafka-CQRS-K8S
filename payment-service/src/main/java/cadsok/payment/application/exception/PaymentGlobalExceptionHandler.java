package cadsok.payment.application.exception;

import cadsok.payment.domain.core.exception.PaymentDomainException;
import cadsok.payment.domain.core.exception.PaymentNotFoundException;
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
public class PaymentGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {PaymentDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(PaymentDomainException PaymentDomainException) {
        log.error(PaymentDomainException.getMessage(), PaymentDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(PaymentDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {PaymentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(PaymentNotFoundException PaymentNotFoundException) {
        log.error(PaymentNotFoundException.getMessage(), PaymentNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(PaymentNotFoundException.getMessage())
                .build();
    }
}
