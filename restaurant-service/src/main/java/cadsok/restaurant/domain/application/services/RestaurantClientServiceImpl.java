package cadsok.restaurant.domain.application.services;

import cadsok.restaurant.data.entity.AcceptanceStatus;
import cadsok.restaurant.domain.application.ports.input.client.RestaurantClientService;
import cadsok.restaurant.domain.application.ports.output.repository.RestaurantRepository;
import cadsok.restaurant.domain.core.event.RestaurantApprovedEvent;
import cadsok.restaurant.domain.core.event.RestaurantEvent;
import cadsok.restaurant.domain.core.service.RestaurantDomainService;
import cadsok.restaurant.messaging.outbox.OutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestaurantClientServiceImpl implements RestaurantClientService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantDomainService restaurantDomainService;
    private final OutboxService outboxService;

    @Value("${kafka.topic-names.restaurant-event}")
    private String topicName;

    @Override
    @Transactional
    public void handleRestaurantAction(String orderId, boolean accept) {
        RestaurantEvent restaurantEvent = restaurantDomainService.handleRestaurantAction(orderId, accept);
        AcceptanceStatus status = restaurantEvent instanceof RestaurantApprovedEvent ?
                AcceptanceStatus.ACCEPTED : AcceptanceStatus.REJECTED;
        restaurantRepository.updateStatus(orderId, status);
        outboxService.handle(restaurantEvent, topicName);
    }

    @Override
    public Map<String, Object> getOrderDetails(String orderId) {
        return restaurantRepository.getOrderDetails(orderId);
    }

    @Override
    public List<Map<String, Object>> getPendingOrders() {
        return restaurantRepository.getPendingOrders();
    }

    @Override
    public List<Map<String, Object>> getAcceptedOrders() {
        return restaurantRepository.getAcceptedOrders();
    }

    @Override
    public List<Map<String, Object>> getRejectedOrders() {
        return restaurantRepository.getRejectedOrders();
    }

}
