package cadsok.restaurant.domain.application.ports.input.service;

import cadsok.restaurant.domain.application.models.create.CreateOrderCommand;
import cadsok.restaurant.domain.application.models.create.CreateOrderResponse;
import cadsok.restaurant.domain.application.models.track.TrackOrderQuery;
import cadsok.restaurant.domain.application.models.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand command);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery query);

}
