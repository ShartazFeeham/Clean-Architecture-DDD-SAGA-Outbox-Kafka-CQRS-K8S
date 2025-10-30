package cadsok.restaurant.domain.core.services;

import commonmodule.domain.values.DateTimeUtil;
import commonmodule.infra.logging.LogAction;
import cadsok.restaurant.domain.core.entity.Order;
import cadsok.restaurant.domain.core.entity.Product;
import cadsok.restaurant.domain.core.entity.Restaurant;
import cadsok.restaurant.domain.core.event.OrderCancelledEvent;
import cadsok.restaurant.domain.core.event.OrderCreatedEvent;
import cadsok.restaurant.domain.core.event.OrderPaidEvent;
import cadsok.restaurant.domain.core.exception.OrderDomainException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    @LogAction(value = "Initializing order", identifiers = {"orderId"})
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
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
    @LogAction(value = "Paying order", identifiers = {"orderId"})
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        return new OrderPaidEvent(order, DateTimeUtil.now());
    }

    @Override
    @LogAction(value = "Approving order", identifiers = {"orderId"})
    public void approveOrder(Order order) {
        order.approve();
    }

    @Override
    @LogAction(value = "Cancelling order payment", identifiers = {"orderId"})
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        return new OrderCancelledEvent(order, DateTimeUtil.now());
    }

    @Override
    @LogAction(value = "Cancelling order", identifiers = {"orderId"})
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
    }

}
