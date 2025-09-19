package cadsok.order.application.service.ports.input.service;

import cadsok.order.application.service.models.create.CreateOrderCommand;
import cadsok.order.application.service.models.create.CreateOrderResponse;
import cadsok.order.application.service.models.track.TrackOrderQuery;
import cadsok.order.application.service.models.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand command);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery query);

}
