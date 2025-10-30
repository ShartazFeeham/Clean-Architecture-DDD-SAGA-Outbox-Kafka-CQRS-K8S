package commonmodule.infra.aop;

import commonmodule.infra.logging.Auditable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Aspect for audit logging.
 * Creates immutable audit trail for security and compliance purposes.
 * Logs to a separate AUDIT logger that should be configured to write to
 * a tamper-proof storage (separate file, database, or SIEM system).
 */
@Aspect
@Component
public class AuditLoggingAspect {

    private static final Logger auditLog = LoggerFactory.getLogger("AUDIT");

    @AfterReturning(value = "@annotation(auditable)", returning = "result")
    public void auditAction(JoinPoint joinPoint, Auditable auditable, Object result) {
        String action = auditable.action();
        String resource = auditable.resource();
        String method = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        
        // Extract resource ID if available
        String resourceId = extractResourceId(result);
        
        // Build audit log entry
        StringBuilder auditEntry = new StringBuilder();
        auditEntry.append("ACTION=").append(action);
        auditEntry.append(", RESOURCE=").append(resource.isEmpty() ? className : resource);
        if (resourceId != null) {
            auditEntry.append(", RESOURCE_ID=").append(resourceId);
        }
        auditEntry.append(", METHOD=").append(method);
        auditEntry.append(", TIMESTAMP=").append(Instant.now().toString());
        auditEntry.append(", STATUS=SUCCESS");
        
        // Write to audit log
        auditLog.info(auditEntry.toString());
    }
    
    /**
     * Attempts to extract resource ID from the result object
     */
    private String extractResourceId(Object result) {
        if (result == null) {
            return null;
        }
        
        try {
            // Try getId()
            var method = result.getClass().getMethod("getId");
            Object id = method.invoke(result);
            if (id != null) {
                // If ID has getValue()
                try {
                    var valueMethod = id.getClass().getMethod("getValue");
                    Object value = valueMethod.invoke(id);
                    return value != null ? value.toString() : id.toString();
                } catch (Exception e) {
                    return id.toString();
                }
            }
        } catch (Exception e) {
            // No ID method available
        }
        
        return null;
    }
}
