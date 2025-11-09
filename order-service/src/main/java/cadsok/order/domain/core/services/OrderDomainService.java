package cadsok.order.domain.core.services;

import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.entity.Restaurant;
import cadsok.order.domain.core.event.*;
import commonmodule.domain.values.Money;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaymentValidEvent validatePayment(Order order, Money amount, String paymentId);

    OrderPaidEvent payOrder(Order order);

    OrderCompletedEvent approveOrder(Order order);

    OrderCancelledEvent cancelOrder(Order order, List<String> failureMessages);

}
