package cadsok.order.domain.application.services.client;

import cadsok.order.domain.application.mapper.OrderDataMapper;
import cadsok.order.domain.application.models.create.CreateOrderCommand;
import cadsok.order.domain.application.models.create.CreateOrderResponse;
import cadsok.order.domain.application.ports.output.repository.CustomerRepository;
import cadsok.order.domain.application.ports.output.repository.OrderRepository;
import cadsok.order.domain.application.ports.output.repository.RestaurantRepository;
import cadsok.order.domain.application.services.events.base.OrderServiceInternalDomainEventPublisher;
import cadsok.order.domain.core.entity.Customer;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.entity.Restaurant;
import cadsok.order.domain.core.event.OrderCreatedEvent;
import cadsok.order.domain.core.exception.OrderDomainException;
import cadsok.order.domain.core.services.OrderDomainService;
import commonmodule.infra.logging.Auditable;
import commonmodule.infra.logging.LogAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
class OrderCreateCommandHandler {

    private final OrderDataMapper orderDomainMapper;
    private final OrderServiceInternalDomainEventPublisher orderServiceInternalDomainEventPublisher;
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    OrderCreateCommandHandler(OrderDataMapper orderDomainMapper,
                              OrderServiceInternalDomainEventPublisher orderServiceInternalDomainEventPublisher,
                              OrderDomainService orderDomainService,
                              OrderRepository orderRepository,
                              CustomerRepository customerRepository,
                              RestaurantRepository restaurantRepository,
                              OrderDataMapper orderDataMapper) {
        this.orderDomainMapper = orderDomainMapper;
        this.orderServiceInternalDomainEventPublisher = orderServiceInternalDomainEventPublisher;
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    @Auditable(action = "ORDER_CREATED", resource = "Order")
    @LogAction(value = "Creating order", identifiers = {"customerId", "restaurantId"})
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        OrderCreatedEvent orderCreatedEvent = persistOrder(command);
        orderServiceInternalDomainEventPublisher.publish(orderCreatedEvent);
        CreateOrderResponse createOrderResponse = orderDomainMapper.orderToCreateOrderResponse(
                orderCreatedEvent.getOrder(), "Order created successfully");

        // TODO: payment outbox

        return createOrderResponse;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        return orderCreatedEvent;
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);
        if (optionalRestaurant.isEmpty()) {
            throw new OrderDomainException("Could not find restaurant with restaurant id: " +
                    createOrderCommand.getRestaurantId());
        }
        return optionalRestaurant.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomerById(customerId);
        if (customer.isEmpty()) {
            throw new OrderDomainException("Could not find customer with customer id: " + customerId);
        }
    }

    private void saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            throw new OrderDomainException("Could not save order!");
        }
    }
}
