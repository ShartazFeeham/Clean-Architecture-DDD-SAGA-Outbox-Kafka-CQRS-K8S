package cadsok.restaurant.infra.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect for automatically logging exceptions across all layers.
 * Provides consistent error logging without explicit log statements.
 */
@Slf4j
@Aspect
@Component
public class LayerExceptionLoggingAspect {

    @AfterThrowing(
        pointcut = "execution(* cadsok.restaurant.domain..*(..))",
        throwing = "exception"
    )
    public void logDomainException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.error("Domain | Exception in {}.{}: {}", 
            className, methodName, exception.getMessage(), exception);
    }

    @AfterThrowing(
        pointcut = "execution(* cadsok.restaurant.data..*(..))",
        throwing = "exception"
    )
    public void logDataException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.error("Data | Exception in {}.{}: {}", 
            className, methodName, exception.getMessage(), exception);
    }

    @AfterThrowing(
        pointcut = "execution(* cadsok.restaurant.messaging..*(..))",
        throwing = "exception"
    )
    public void logMessagingException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.error("Messaging | Exception in {}.{}: {}", 
            className, methodName, exception.getMessage(), exception);
    }

    @AfterThrowing(
        pointcut = "execution(* cadsok.restaurant.application..*(..))",
        throwing = "exception"
    )
    public void logApplicationException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.error("Application | Exception in {}.{}: {}", 
            className, methodName, exception.getMessage(), exception);
    }
}
