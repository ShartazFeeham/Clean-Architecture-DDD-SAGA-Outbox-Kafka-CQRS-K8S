package cadsok.restaurant.data.adapter;

import cadsok.restaurant.data.entity.AcceptanceStatus;
import cadsok.restaurant.data.entity.RestaurantOrderEntity;
import cadsok.restaurant.data.mapper.RestaurantOrderMapper;
import cadsok.restaurant.data.repository.RestaurantOrderJpaRepository;
import cadsok.restaurant.domain.application.ports.output.repository.RestaurantRepository;
import cadsok.restaurant.domain.core.exception.RestaurantDomainException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantOrderMapper mapper;
    private final RestaurantOrderJpaRepository jpaRepository;

    @Override
    @Transactional
    public void saveOrder(String orderId, String orderDetailJson, AcceptanceStatus status) {
        RestaurantOrderEntity dbEntity = mapper.toDbEntity(orderId, orderDetailJson, status);
        jpaRepository.save(dbEntity);
    }

    @Override
    public Map<String, Object> getOrderDetails(String orderId) {
        RestaurantOrderEntity order = findOrThrowError(orderId);
        return toMap(order);
    }

    @Override
    public List<Map<String, Object>> getPendingOrders() {
        return filterByStatusAndConvertToListOfMap(AcceptanceStatus.PENDING);
    }

    @Override
    public List<Map<String, Object>> getAcceptedOrders() {
        return filterByStatusAndConvertToListOfMap(AcceptanceStatus.ACCEPTED);
    }

    @Override
    public List<Map<String, Object>> getRejectedOrders() {
        return filterByStatusAndConvertToListOfMap(AcceptanceStatus.REJECTED);
    }

    @Override
    @Transactional
    public void updateStatus(String orderId, AcceptanceStatus status) {
        RestaurantOrderEntity order = findOrThrowError(orderId);
        order.setStatus(status);
        jpaRepository.save(order);
    }

    private RestaurantOrderEntity findOrThrowError(String orderId) {
        return jpaRepository
                .findById(getId(orderId))
                .orElseThrow(() -> new RestaurantDomainException("Order not found with id " + orderId));
    }

    private List<Map<String, Object>> filterByStatusAndConvertToListOfMap(AcceptanceStatus status) {
        return jpaRepository.findByStatus(status)
                .stream().map(RestaurantRepositoryImpl::toMap)
                .collect(Collectors.toList());
    }

    private static Map<String, Object> toMap(RestaurantOrderEntity order) {
        return Map.of(
                "orderId", order.getOrderId(),
                "detailJson", order.getDetailJson(),
                "status", order.getStatus()
        );
    }

    private UUID getId(String orderId) {
        return UUID.fromString(orderId);
    }
}
