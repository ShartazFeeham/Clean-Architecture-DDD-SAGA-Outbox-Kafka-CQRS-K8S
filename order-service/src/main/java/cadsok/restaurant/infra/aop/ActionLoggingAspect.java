package cadsok.restaurant.infra.aop;

import commonmodule.infra.logging.LogAction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Aspect for logging methods annotated with @LogAction.
 * Logs method entry, success, and failure with structured format.
 * Uses the target class's logger for proper class identification.
 */
@Aspect
@Component
public class ActionLoggingAspect {

    private static final Random random = new Random();
    private static final String TRACKING_HASH_KEY = "trackingHash";

    @Around("@annotation(logAction)")
    public Object logAction(ProceedingJoinPoint joinPoint, LogAction logAction) throws Throwable {
        // Check sample rate - skip logging if not sampled
        boolean shouldLog = shouldSample(logAction.sampleRate());

        // Get logger for the actual target class, not the aspect
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        String layer = determineLayer(joinPoint);
        String action = logAction.value();
        String[] identifierLabels = logAction.identifiers();

        // Extract identifiers from method arguments
        String identifiersString = extractIdentifiers(joinPoint, identifierLabels);
        String actionWithIds = identifiersString.isEmpty()
                ? action
                : action + ", " + identifiersString;

        // Mask sensitive data in action string
        actionWithIds = maskSensitiveData(actionWithIds);

        // Start timing
        long startTime = System.currentTimeMillis();

        // Log method entry - STARTED (only if sampled)
        String shortTrackingHash = generateShortTrackingHash();
        
        // Store tracking hash in MDC for async operations
        MDC.put(TRACKING_HASH_KEY, shortTrackingHash);
        
        if (shouldLog) {
            log.info("[{}] {} | {} - started", shortTrackingHash, layer, actionWithIds);
        }

        try {
            // Execute the method
            Object result = joinPoint.proceed();

            // Calculate execution time
            long duration = System.currentTimeMillis() - startTime;

            // Log success - COMPLETED with duration (only if sampled)
            if (shouldLog) {
                if (logAction.logResult() && result != null) {
                    log.info("[{}] {} | {} - completed ({}ms) [result: {}]",
                            shortTrackingHash, layer, actionWithIds, duration, formatResult(result));
                } else {
                    log.info("[{}] {} | {} - completed ({}ms)", shortTrackingHash, layer, actionWithIds, duration);
                }
            }

            return result;

        } catch (Throwable e) {
            // Calculate execution time even for failures
            long duration = System.currentTimeMillis() - startTime;

            // Log failure - FAILED with duration and context (ALWAYS log errors, ignore sample rate)
            log.error("{} | {} - failed ({}ms): {}", layer, actionWithIds, duration, e.getMessage(), e);
            throw e;
        } finally {
            // Clean up MDC
            MDC.remove(TRACKING_HASH_KEY);
        }
    }

    private String generateShortTrackingHash() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(getRandomNumberOrUpperCaseChar());
        }
        return sb.toString();
    }

    private char getRandomNumberOrUpperCaseChar() {
        int random = ActionLoggingAspect.random.nextInt(36);
        if (random < 10) {
            return (char) ('0' + random);
        } else {
            return (char) ('A' + random - 10);
        }
    }

    /**
     * Extracts identifiers from method arguments based on provided labels.
     * Attempts to call getId() or getValue() on each argument.
     */
    private String extractIdentifiers(ProceedingJoinPoint joinPoint, String[] identifierLabels) {
        if (identifierLabels == null || identifierLabels.length == 0) {
            return "";
        }

        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int argIndex = 0;

        for (int i = 0; i < identifierLabels.length && argIndex < args.length; i++) {
            Object arg = args[argIndex];
            String label = identifierLabels[i];
            String idValue = extractIdFromObject(arg);

            if (idValue != null) {
                if (!result.isEmpty()) {
                    result.append(", ");
                }
                result.append(label).append(": ").append(idValue);
            }
            argIndex++;
        }

        return result.toString();
    }

    /**
     * Extracts ID from a single object by trying various methods.
     */
    private String extractIdFromObject(Object obj) {
        if (obj == null) {
            return null;
        }

        // Try getId()
        try {
            var method = obj.getClass().getMethod("getId");
            Object id = method.invoke(obj);
            if (id != null) {
                // If ID has getValue(), call it (for value objects)
                try {
                    var valueMethod = id.getClass().getMethod("getValue");
                    Object value = valueMethod.invoke(id);
                    return value != null ? value.toString() : id.toString();
                } catch (Exception e) {
                    return id.toString();
                }
            }
        } catch (Exception e) {
            // Try getValue() directly
            try {
                var valueMethod = obj.getClass().getMethod("getValue");
                Object value = valueMethod.invoke(obj);
                return value != null ? value.toString() : null;
            } catch (Exception ex) {
                // If object itself is a simple type (UUID, String, etc.), return it
                if (obj instanceof java.util.UUID || obj instanceof String ||
                        obj instanceof Number) {
                    return obj.toString();
                }
            }
        }

        return null;
    }

    private String formatResult(Object result) {
        if (result == null) {
            return "null";
        }
        return result.getClass().getSimpleName();
    }

    /**
     * Determines if this invocation should be logged based on sample rate.
     */
    private boolean shouldSample(double sampleRate) {
        if (sampleRate >= 1.0) {
            return true; // Always log
        }
        if (sampleRate <= 0.0) {
            return false; // Never log
        }
        return Math.random() < sampleRate;
    }

    /**
     * Masks sensitive data like credit cards, emails, passwords, etc.
     */
    private String maskSensitiveData(String data) {
        if (data == null) {
            return null;
        }

        // Mask credit card numbers (16 digits)
        data = data.replaceAll("\\b\\d{4}[\\s-]?\\d{4}[\\s-]?\\d{4}[\\s-]?\\d{4}\\b", "****-****-****-****");

        // Mask email addresses
        data = data.replaceAll("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b", "***@***.com");

        // Mask passwords (case-insensitive pattern)
        data = data.replaceAll("(?i)(password|pwd|pass)\\s*[:=]\\s*\"?[^\"\\s,]+\"?", "$1=***");

        // Mask tokens/keys
        data = data.replaceAll("(?i)(token|key|secret|api[_-]?key)\\s*[:=]\\s*\"?[^\"\\s,]+\"?", "$1=***");

        return data;
    }

    /**
     * Determines the layer based on the package name of the class
     */
    private String determineLayer(ProceedingJoinPoint joinPoint) {
        String packageName = joinPoint.getSignature().getDeclaringType().getPackage().getName();

        if (packageName.contains(".domain.")) {
            return "Domain";
        } else if (packageName.contains(".data.")) {
            return "Data";
        } else if (packageName.contains(".messaging.")) {
            return "Messaging";
        } else if (packageName.contains(".application.")) {
            return "Application";
        } else {
            // Default fallback
            return "Service";
        }
    }
}
