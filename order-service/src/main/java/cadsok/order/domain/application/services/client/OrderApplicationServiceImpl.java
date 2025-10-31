package cadsok.order.domain.application.services.client;

import cadsok.order.domain.application.models.create.CreateOrderCommand;
import cadsok.order.domain.application.models.create.CreateOrderResponse;
import cadsok.order.domain.application.models.track.TrackOrderQuery;
import cadsok.order.domain.application.models.track.TrackOrderResponse;
import cadsok.order.domain.application.ports.input.service.OrderApplicationService;
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
