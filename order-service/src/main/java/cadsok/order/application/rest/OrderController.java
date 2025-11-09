package cadsok.order.application.rest;

import cadsok.order.domain.application.models.create.CreateOrderCommand;
import cadsok.order.domain.application.models.create.CreateOrderResponse;
import cadsok.order.domain.application.models.track.TrackOrderQuery;
import cadsok.order.domain.application.models.track.TrackOrderResponse;
import cadsok.order.domain.application.ports.input.service.OrderApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        return ResponseEntity.ok(createOrderResponse);
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID trackingId) {
       TrackOrderResponse trackOrderResponse =
               orderApplicationService.trackOrder(TrackOrderQuery.builder().orderTrackingId(trackingId).build());
       return  ResponseEntity.ok(trackOrderResponse);
    }

    @DeleteMapping
    public ResponseEntity<String> cancelOrder(@RequestParam String trackingId) {
        orderApplicationService.cancelOrder(trackingId);
        return  ResponseEntity.ok("Request submitted for cancelling order.");
    }
}