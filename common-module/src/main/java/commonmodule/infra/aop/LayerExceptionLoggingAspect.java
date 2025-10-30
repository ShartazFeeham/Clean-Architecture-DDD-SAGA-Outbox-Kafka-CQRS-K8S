package commonmodule.infra.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect for automatically logging exceptions across all layers.
 * Provides consistent error logging without explicit log statements.
 * Uses package naming conventions to determine layer (domain, data, messaging, application).
 */
@Slf4j
@Aspect
// @Component  // Disabled - conflicts with Spring Data JPA proxies
public class LayerExceptionLoggingAspect {

    @AfterThrowing(
        pointcut = "execution(* *..*..domain..*(..))",
        throwing = "exception"
    )
    public void logDomainException(JoinPoint joinPoint, Throwable exception) {
        logLayerException("Domain", joinPoint, exception);
    }

    @AfterThrowing(
        pointcut = "execution(* *..*..data..*(..))",
        throwing = "exception"
    )
    public void logDataException(JoinPoint joinPoint, Throwable exception) {
        logLayerException("Data", joinPoint, exception);
    }

    @AfterThrowing(
        pointcut = "execution(* *..*..messaging..*(..))",
        throwing = "exception"
    )
    public void logMessagingException(JoinPoint joinPoint, Throwable exception) {
        logLayerException("Messaging", joinPoint, exception);
    }

    @AfterThrowing(
        pointcut = "execution(* *..*..application..*(..))",
        throwing = "exception"
    )
    public void logApplicationException(JoinPoint joinPoint, Throwable exception) {
        logLayerException("Application", joinPoint, exception);
    }

    private void logLayerException(String layer, JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.error("{} | Exception in {}.{}: {}", 
            layer, className, methodName, exception.getMessage(), exception);
    }
}
