package cadsok.payment.messaging.outbox;


import cadsok.payment.domain.core.event.PaymentEvent;

public interface OutboxService {

    void handle(PaymentEvent restaurantEvent, String topicName);

}
