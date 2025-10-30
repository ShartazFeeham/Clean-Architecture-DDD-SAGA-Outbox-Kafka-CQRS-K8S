package commonmodule.infra.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark methods that should be logged with structured format.
 * Automatically logs method entry, success, and failure.
 * Layer is determined automatically from package name.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAction {
    /**
     * Description of the action being performed
     */
    String value();

    /**
     * Labels for identifiers to extract from method arguments.
     * The aspect will attempt to extract IDs from the first N arguments
     * where N = identifiers.length, by calling getId() or similar methods.
     * 
     * Example: identifiers = {"orderId", "customerId"}
     * Result: "action, orderId: xxx, customerId: yyy - started"
     */
    String[] identifiers() default {};

    /**
     * Whether to include method arguments in the log
     */
    boolean logArgs() default false;

    /**
     * Whether to include return value in the log
     */
    boolean logResult() default false;
    
    /**
     * Sample rate for logging (0.0 to 1.0).
     * 1.0 = log every invocation (default)
     * 0.1 = log 10% of invocations
     * 0.01 = log 1% of invocations
     * Useful for high-frequency methods to reduce log volume.
     */
    double sampleRate() default 1.0;
}
