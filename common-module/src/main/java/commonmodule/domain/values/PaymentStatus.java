package commonmodule.domain.values;

public enum PaymentStatus {
    INITIALIZED, // Will be initialized from api call from client and event will be published
    PROCESSING, // Restaurant service will accept the event and publish another one if order is valid. Receiving which, the payment service will switch to processing state
    COMPLETED, // Another dummy event listener here will work as if it is a payment gateway which will accept processing event and 80% times set to completed and 20% times failed
    CANCELLED, // An event listener that will listen for event triggered from restaurant service for cancelling order request
    FAILED // The 20% times dummy PG say failed
}
