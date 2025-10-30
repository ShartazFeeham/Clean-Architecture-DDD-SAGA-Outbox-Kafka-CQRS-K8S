package cadsok.order.domain.application.ports.input.message.listener.restaurant.approval;

import cadsok.order.domain.application.models.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);

}
