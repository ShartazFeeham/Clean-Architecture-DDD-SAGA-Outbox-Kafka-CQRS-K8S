package cadsok.restaurant.application.rest;

import cadsok.restaurant.domain.application.ports.input.client.RestaurantClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/restaurant-orders")
public class RestaurantController {

    private final RestaurantClientService restaurantClientService;

    @PostMapping
    public ResponseEntity<String> handleRestaurantAction(@RequestParam String orderId, @RequestParam boolean accept){
        restaurantClientService.handleRestaurantAction(orderId, accept);
        return ResponseEntity.ok("Request sent successful");
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getOrderDetails(@RequestParam String orderId){
        return ResponseEntity.ok(restaurantClientService.getOrderDetails(orderId));
    }

    @GetMapping("/list/pending")
    public ResponseEntity<List<Map<String, Object>>> getPendingOrders(){
        return ResponseEntity.ok(restaurantClientService.getPendingOrders());
    }

    @GetMapping("/list/accepted")
    public ResponseEntity<List<Map<String, Object>>> getAcceptedOrders(){
        return ResponseEntity.ok(restaurantClientService.getAcceptedOrders());
    }

    @GetMapping("/list/rejected")
    public ResponseEntity<List<Map<String, Object>>> getRejectedOrders(){
        return ResponseEntity.ok(restaurantClientService.getRejectedOrders());
    }
}
