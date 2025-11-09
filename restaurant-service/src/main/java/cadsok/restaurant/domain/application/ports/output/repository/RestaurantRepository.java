package cadsok.restaurant.domain.application.ports.output.repository;


import cadsok.restaurant.data.entity.AcceptanceStatus;

import java.util.List;
import java.util.Map;

public interface RestaurantRepository {

    void saveOrder(String orderId, String orderDetailJson, AcceptanceStatus status);

    Map<String, Object> getOrderDetails(String orderId);

    List<Map<String, Object>> getPendingOrders();

    List<Map<String, Object>> getAcceptedOrders();

    List<Map<String, Object>> getRejectedOrders();

    void updateStatus(String orderId, AcceptanceStatus status);
}
