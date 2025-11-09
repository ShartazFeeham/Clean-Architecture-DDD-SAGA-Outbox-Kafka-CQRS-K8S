package cadsok.restaurant.domain.application.ports.input.client;


import java.util.List;
import java.util.Map;

public interface RestaurantClientService {

    void handleRestaurantAction(String orderId, boolean accept);

    Map<String, Object> getOrderDetails(String orderId);

    List<Map<String, Object>> getPendingOrders();

    List<Map<String, Object>> getAcceptedOrders();

    List<Map<String, Object>> getRejectedOrders();

}
