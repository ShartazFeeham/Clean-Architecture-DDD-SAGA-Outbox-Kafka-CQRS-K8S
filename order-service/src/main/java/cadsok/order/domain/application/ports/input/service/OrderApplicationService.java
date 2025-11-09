package cadsok.order.domain.application.ports.input.service;

import cadsok.order.domain.application.models.create.CreateOrderCommand;
import cadsok.order.domain.application.models.create.CreateOrderResponse;
import cadsok.order.domain.application.models.track.TrackOrderQuery;
import cadsok.order.domain.application.models.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand command);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery query);

    void cancelOrder(String orderId);
}
