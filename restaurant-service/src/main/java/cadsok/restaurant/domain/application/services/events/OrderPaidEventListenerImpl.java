package cadsok.restaurant.domain.application.services.events;

import cadsok.restaurant.data.entity.AcceptanceStatus;
import cadsok.restaurant.domain.application.ports.input.event.OrderPaidEventListener;
import cadsok.restaurant.domain.application.ports.output.repository.RestaurantRepository;
import cadsok.restaurant.domain.core.service.RestaurantDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderPaidEventListenerImpl implements OrderPaidEventListener {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantDomainService restaurantDomainService;

    @Override
    @Transactional
    public void handleOrderPaidEvent(String orderId, String detailJson) {
        restaurantDomainService.validateNewOrder(orderId, detailJson);
        restaurantRepository.saveOrder(orderId, detailJson, AcceptanceStatus.PENDING);
    }

}
