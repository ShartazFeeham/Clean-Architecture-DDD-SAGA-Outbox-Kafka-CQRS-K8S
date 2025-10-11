package cadsok.restaurant.domain.application.services;

import cadsok.restaurant.domain.application.models.create.CreateOrderCommand;
import cadsok.restaurant.domain.application.models.create.CreateOrderResponse;
import cadsok.restaurant.domain.application.models.track.TrackOrderQuery;
import cadsok.restaurant.domain.application.models.track.TrackOrderResponse;
import cadsok.restaurant.domain.application.ports.input.service.OrderApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackingQueryHandler orderTrackingQueryHandler;

    OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
                                OrderTrackingQueryHandler orderTrackingQueryHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackingQueryHandler = orderTrackingQueryHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        return orderCreateCommandHandler.createOrder(command);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        return orderTrackingQueryHandler.trackOrder(query);
    }
}
