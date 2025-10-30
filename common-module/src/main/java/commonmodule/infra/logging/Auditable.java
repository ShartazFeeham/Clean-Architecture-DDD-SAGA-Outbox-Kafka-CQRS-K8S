package commonmodule.infra.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks methods that require audit logging.
 * Audit logs are separate from regular application logs and are used for
 * security, compliance, and accountability purposes.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {
    /**
     * The action being audited (e.g., "ORDER_CREATED", "PAYMENT_PROCESSED")
     */
    String action();
    
    /**
     * The resource type being acted upon (e.g., "Order", "Payment")
     */
    String resource() default "";
}
