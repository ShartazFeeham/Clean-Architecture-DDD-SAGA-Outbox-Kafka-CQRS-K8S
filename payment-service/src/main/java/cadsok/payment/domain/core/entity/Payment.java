package cadsok.payment.domain.core.entity;

import cadsok.payment.domain.core.exception.PaymentDomainException;
import cadsok.payment.domain.core.values.PaymentId;
import commonmodule.domain.entity.AggregateRoot;
import commonmodule.domain.values.*;

import java.util.UUID;

public class Payment extends AggregateRoot<OrderId> {

    private PaymentId paymentId;
    private final OrderId orderId;
    private final CustomerId customerId;
    private final Money price;
    private PaymentStatus paymentStatus;

    public Payment(OrderId orderId, CustomerId customerId, Money price) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.price = price;
    }

    public PaymentId getPaymentId() {
        return paymentId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Money getPrice() {
        return price;
    }

    public void process() {
        this.paymentStatus = PaymentStatus.PROCESSING;
    }

    public void validate() {
        if (orderId == null || orderId.getValue() == null) {
            throw new PaymentDomainException("Invalid orderId, must not be null.");
        }
        if (customerId == null || customerId.getValue() == null) {
            throw new PaymentDomainException("Invalid customerId, must not be null.");
        }
        if (price == null || !price.isGreaterThanZero()) {
            throw new PaymentDomainException("Invalid amount of payment!");
        }
    }

    public void initialize() {
        this.paymentId = new PaymentId(UUID.randomUUID());
        this.paymentStatus = PaymentStatus.INITIALIZED;
    }

    public void pay() {
        this.paymentStatus = PaymentStatus.COMPLETED;
    }

    public void fail() {
        this.paymentStatus = PaymentStatus.FAILED;
    }

    public void cancel() {
        this.paymentStatus = PaymentStatus.CANCELLED;
    }

}
