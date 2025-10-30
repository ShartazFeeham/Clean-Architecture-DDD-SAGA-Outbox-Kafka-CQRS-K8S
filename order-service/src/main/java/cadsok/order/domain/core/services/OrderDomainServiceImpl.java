package cadsok.order.domain.core.services;

import commonmodule.domain.values.DateTimeUtil;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.entity.Product;
import cadsok.order.domain.core.entity.Restaurant;
import cadsok.order.domain.core.event.OrderCancelledEvent;
import cadsok.order.domain.core.event.OrderCreatedEvent;
import cadsok.order.domain.core.event.OrderPaidEvent;
import cadsok.order.domain.core.exception.OrderDomainException;

import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        return new OrderCreatedEvent(order, DateTimeUtil.now());
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(orderItem -> {
            Product currentProduct = orderItem.getProduct();
            boolean productFound = false;
            
            for (Product restaurantProduct : restaurant.getProducts()) {
                if (currentProduct.equals(restaurantProduct)) {
                    currentProduct.updateWithConfirmedNameAndPrice(
                        restaurantProduct.getName(), 
                        restaurantProduct.getPrice()
                    );
                    productFound = true;
                    break;
                }
            }
            
            if (!productFound) {
                throw new OrderDomainException(
                    "Product with id " + currentProduct.getId().getValue() + 
                    " is not available in restaurant with id " + restaurant.getId().getValue()
                );
            }
        });
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant with id " + restaurant.getId().getValue() + " is not active");
        }
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        return new OrderPaidEvent(order, DateTimeUtil.now());
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        return new OrderCancelledEvent(order, DateTimeUtil.now());
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
    }

}
