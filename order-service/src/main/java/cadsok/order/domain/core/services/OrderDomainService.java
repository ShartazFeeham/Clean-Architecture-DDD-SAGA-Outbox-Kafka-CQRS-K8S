package cadsok.order.domain.core.services;

import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.entity.Restaurant;
import cadsok.order.domain.core.event.OrderCancelledEvent;
import cadsok.order.domain.core.event.OrderCreatedEvent;
import cadsok.order.domain.core.event.OrderPaidEvent;
import cadsok.order.domain.core.event.OrderPaymentValidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaymentValidEvent validateAndPayOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);

}
