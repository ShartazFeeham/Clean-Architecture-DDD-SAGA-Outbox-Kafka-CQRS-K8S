package cadsok.order.domain.application.ports.input.message.listener.restaurant;


public interface RestaurantMessageListener {

    void orderApproved(String orderId, boolean approved);

}
