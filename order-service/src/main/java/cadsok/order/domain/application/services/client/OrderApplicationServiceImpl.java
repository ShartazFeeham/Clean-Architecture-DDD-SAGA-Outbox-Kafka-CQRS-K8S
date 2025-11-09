package cadsok.order.domain.application.services.client;

import cadsok.order.domain.application.models.create.CreateOrderCommand;
import cadsok.order.domain.application.models.create.CreateOrderResponse;
import cadsok.order.domain.application.models.track.TrackOrderQuery;
import cadsok.order.domain.application.models.track.TrackOrderResponse;
import cadsok.order.domain.application.ports.input.message.listener.restaurant.RestaurantMessageListener;
import cadsok.order.domain.application.ports.input.service.OrderApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackingQueryHandler orderTrackingQueryHandler;
    private final RestaurantMessageListener restaurantMessageListener;

    OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
                                OrderTrackingQueryHandler orderTrackingQueryHandler,
                                RestaurantMessageListener restaurantMessageListener) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackingQueryHandler = orderTrackingQueryHandler;
        this.restaurantMessageListener = restaurantMessageListener;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        return orderCreateCommandHandler.createOrder(command);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        return orderTrackingQueryHandler.trackOrder(query);
    }

    @Override
    public void cancelOrder(String orderId) {
        restaurantMessageListener.orderApproved(orderId, false);
    }
}
