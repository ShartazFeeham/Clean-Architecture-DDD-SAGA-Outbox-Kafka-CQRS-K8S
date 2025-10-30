package cadsok.restaurant.infra.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Rate-limiting aspect to prevent log storms.
 * Limits the number of error logs per method to prevent flooding the log system
 * in case of repeated failures (e.g., database connection issues, infinite loops).
 */
@Aspect
@Component
public class RateLimitedLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(RateLimitedLoggingAspect.class);
    
    // Track last log time and count per method
    private final ConcurrentHashMap<String, LogRateTracker> logTrackers = new ConcurrentHashMap<>();
    
    // Configuration
    private static final long RATE_LIMIT_WINDOW_MS = 60000; // 1 minute
    private static final long MAX_LOGS_PER_WINDOW = 10; // Max 10 error logs per minute per method
    
    /**
     * Intercepts exceptions and applies rate limiting to error logs
     */
    @AfterThrowing(
        pointcut = "within(cadsok.restaurant..*) && !within(cadsok.restaurant.infra.aop..*)",
        throwing = "exception"
    )
    public void rateLimitedErrorLog(JoinPoint joinPoint, Throwable exception) {
        String methodKey = joinPoint.getSignature().toShortString();
        Logger targetLog = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        
        LogRateTracker tracker = logTrackers.computeIfAbsent(methodKey, k -> new LogRateTracker());
        
        if (tracker.shouldLog()) {
            targetLog.error("Rate-limited error in {}: {}", methodKey, exception.getMessage(), exception);
        } else {
            tracker.incrementSuppressed();
            // Log suppression warning once per window
            if (tracker.shouldLogSuppression()) {
                targetLog.warn("Suppressing repeated errors in {} ({} errors suppressed in last {}ms)", 
                    methodKey, tracker.getSuppressedCount(), RATE_LIMIT_WINDOW_MS);
            }
        }
    }
    
    /**
     * Tracks log rate for a specific method
     */
    private static class LogRateTracker {
        private final AtomicLong lastWindowStart = new AtomicLong(System.currentTimeMillis());
        private final AtomicLong logsInWindow = new AtomicLong(0);
        private final AtomicLong suppressedCount = new AtomicLong(0);
        private final AtomicLong lastSuppressionLog = new AtomicLong(0);
        
        boolean shouldLog() {
            long now = System.currentTimeMillis();
            long windowStart = lastWindowStart.get();
            
            // Reset window if expired
            if (now - windowStart > RATE_LIMIT_WINDOW_MS) {
                lastWindowStart.set(now);
                logsInWindow.set(0);
                suppressedCount.set(0);
            }
            
            // Check if under limit
            if (logsInWindow.get() < MAX_LOGS_PER_WINDOW) {
                logsInWindow.incrementAndGet();
                return true;
            }
            
            return false;
        }
        
        void incrementSuppressed() {
            suppressedCount.incrementAndGet();
        }
        
        long getSuppressedCount() {
            return suppressedCount.get();
        }
        
        boolean shouldLogSuppression() {
            long now = System.currentTimeMillis();
            long lastLog = lastSuppressionLog.get();
            
            // Log suppression message once per window
            if (now - lastLog > RATE_LIMIT_WINDOW_MS) {
                lastSuppressionLog.set(now);
                return true;
            }
            return false;
        }
    }
}
