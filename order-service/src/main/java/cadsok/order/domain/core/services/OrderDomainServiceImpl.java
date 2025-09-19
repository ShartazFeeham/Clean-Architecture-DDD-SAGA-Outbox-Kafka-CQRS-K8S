package cadsok.order.domain.core.services;

import cadsok.common.domain.values.DateTimeUtil;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.entity.Product;
import cadsok.order.domain.core.entity.Restaurant;
import cadsok.order.domain.core.event.OrderCancelledEvent;
import cadsok.order.domain.core.event.OrderCreatedEvent;
import cadsok.order.domain.core.event.OrderPaidEvent;
import cadsok.order.domain.core.exception.OrderDomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDomainServiceImpl.class);

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        LOG.info("Order with id: {} has been initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, DateTimeUtil.now());
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(item -> restaurant.getProducts().forEach(restaurantProduct -> {
            Product currentProduct = item.getProduct();
            if (currentProduct.equals(restaurantProduct)) {
                currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(), restaurantProduct.getPrice());
            }
        }));
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant with id " + restaurant.getId().getValue() + " is not active");
        }
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        LOG.info("Order with id: {} has been paid", order.getId().getValue());
        return new OrderPaidEvent(order, DateTimeUtil.now());
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        LOG.info("Order with id: {} has been approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        LOG.info("Order payment with id: {} has been cancelled", order.getId().getValue());
        return new OrderCancelledEvent(order, DateTimeUtil.now());
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        LOG.info("Order with id: {} has been cancelled", order.getId().getValue());
    }

}
