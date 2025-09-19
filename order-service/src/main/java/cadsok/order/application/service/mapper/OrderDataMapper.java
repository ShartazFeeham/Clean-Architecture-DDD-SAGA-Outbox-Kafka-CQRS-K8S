package cadsok.order.application.service.mapper;

import cadsok.common.domain.values.CustomerId;
import cadsok.common.domain.values.Money;
import cadsok.common.domain.values.ProductId;
import cadsok.common.domain.values.RestaurantId;
import cadsok.order.application.service.models.create.CreateOrderCommand;
import cadsok.order.application.service.models.create.CreateOrderResponse;
import cadsok.order.application.service.models.create.OrderAddress;
import cadsok.order.application.service.models.track.TrackOrderResponse;
import cadsok.order.application.service.models.create.OrderItemDTO;
import cadsok.order.domain.core.entity.*;
import cadsok.order.domain.core.values.StreetAddress;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.Builder.builder()
                .id(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                                new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.Builder.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

//    public OrderPaymentEventPayload orderCreatedEventToOrderPaymentEventPayload(OrderCreatedEvent orderCreatedEvent) {
//        return OrderPaymentEventPayload.builder()
//                .customerId(orderCreatedEvent.getOrder().getCustomerId().getValue().toString())
//                .orderId(orderCreatedEvent.getOrder().getId().getValue().toString())
//                .price(orderCreatedEvent.getOrder().getPrice().getAmount())
//                .createdAt(orderCreatedEvent.getCreatedAt())
//                .paymentOrderStatus(PaymentOrderStatus.PENDING.name())
//                .build();
//    }
//
//    public OrderPaymentEventPayload orderCancelledEventToOrderPaymentEventPayload(OrderCancelledEvent
//                                                                                          orderCancelledEvent) {
//        return OrderPaymentEventPayload.builder()
//                .customerId(orderCancelledEvent.getOrder().getCustomerId().getValue().toString())
//                .orderId(orderCancelledEvent.getOrder().getId().getValue().toString())
//                .price(orderCancelledEvent.getOrder().getPrice().getAmount())
//                .createdAt(orderCancelledEvent.getCreatedAt())
//                .paymentOrderStatus(PaymentOrderStatus.CANCELLED.name())
//                .build();
//    }
//
//    public OrderApprovalEventPayload orderPaidEventToOrderApprovalEventPayload(OrderPaidEvent orderPaidEvent) {
//        return OrderApprovalEventPayload.builder()
//                .orderId(orderPaidEvent.getOrder().getId().getValue().toString())
//                .restaurantId(orderPaidEvent.getOrder().getRestaurantId().getValue().toString())
//                .restaurantOrderStatus(RestaurantOrderStatus.PAID.name())
//                .products(orderPaidEvent.getOrder().getItems().stream().map(orderItem ->
//                        OrderApprovalEventProduct.builder()
//                                .id(orderItem.getProduct().getId().getValue().toString())
//                                .quantity(orderItem.getQuantity())
//                                .build()).collect(Collectors.toList()))
//                .price(orderPaidEvent.getOrder().getPrice().getAmount())
//                .createdAt(orderPaidEvent.getCreatedAt())
//                .build();
//    }
//
//    public Customer customerModelToCustomer(CustomerModel customerModel) {
//        return new Customer(new CustomerId(UUID.fromString(customerModel.getId())),
//                customerModel.getUsername(),
//                customerModel.getFirstName(),
//                customerModel.getLastName());
//    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            @NotNull List<OrderItemDTO> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        OrderItem.Builder.builder()
                                .product(new Product(new ProductId(orderItem.getProductId())))
                                .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                .subTotal(new Money(orderItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }
}