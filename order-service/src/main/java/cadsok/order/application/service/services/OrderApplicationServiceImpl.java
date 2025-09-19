package cadsok.order.application.service.services;

import cadsok.order.application.service.models.create.CreateOrderCommand;
import cadsok.order.application.service.models.create.CreateOrderResponse;
import cadsok.order.application.service.models.track.TrackOrderQuery;
import cadsok.order.application.service.models.track.TrackOrderResponse;
import cadsok.order.application.service.ports.input.service.OrderApplicationService;
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
