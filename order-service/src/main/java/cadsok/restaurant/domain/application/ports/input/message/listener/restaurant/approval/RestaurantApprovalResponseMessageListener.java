package cadsok.restaurant.domain.application.ports.input.message.listener.restaurant.approval;

import cadsok.restaurant.domain.application.models.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);

}
