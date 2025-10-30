package commonmodule.infra.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Helper for logging async operations with tracking hash support.
 * Captures tracking hash from calling thread and restores it in async callback.
 */
public class AsyncLoggingHelper {

    private static final String TRACKING_HASH_KEY = "trackingHash";

    /**
     * Logs success/failure of async operation with tracking hash.
     * Call this in your async callback (e.g., whenComplete, thenAccept).
     *
     * @param targetClass The class performing the async operation
     * @param layer The layer name (Domain, Data, Messaging, Application)
     * @param action The action being performed
     * @param successMessage Message to log on success
     * @param failureMessage Message to log on failure
     * @param exception The exception if failed, null if successful
     */
    public static void logAsync(Class<?> targetClass, String layer, String action, 
                                String successMessage, String failureMessage, Throwable exception) {
        String trackingHash = MDC.get(TRACKING_HASH_KEY);
        Logger logger = LoggerFactory.getLogger(targetClass);

        try {
            if (exception != null) {
                if (trackingHash != null) {
                    logger.error("[{}] {} | {} {}", trackingHash, layer, action, failureMessage, exception);
                } else {
                    logger.error("{} | {} {}", layer, action, failureMessage, exception);
                }
            } else {
                if (trackingHash != null) {
                    logger.info("[{}] {} | {} {}", trackingHash, layer, action, successMessage);
                } else {
                    logger.info("{} | {} {}", layer, action, successMessage);
                }
            }
        } finally {
            if (trackingHash != null) {
                MDC.remove(TRACKING_HASH_KEY);
            }
        }
    }

    /**
     * Captures the current tracking hash to be used in async operations.
     * Store this before starting async work, then pass to restoreTrackingHash() in the callback.
     */
    public static String captureTrackingHash() {
        return MDC.get(TRACKING_HASH_KEY);
    }

    /**
     * Restores tracking hash in async thread.
     * Call this at the start of your async callback.
     */
    public static void restoreTrackingHash(String trackingHash) {
        if (trackingHash != null) {
            MDC.put(TRACKING_HASH_KEY, trackingHash);
        }
    }
}
