package cadsok.order.domain.core.event;

import cadsok.order.domain.core.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaymentValidEvent extends OrderEvent {

    private final boolean isValid;
    private final String paymentId;

    public OrderPaymentValidEvent(Order order, ZonedDateTime createdAt, boolean isValid, String paymentId) {
        super(order, createdAt);
        this.isValid = isValid;
        this.paymentId = paymentId;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
