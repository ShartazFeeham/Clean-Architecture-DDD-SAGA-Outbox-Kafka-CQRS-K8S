package cadsok.order.application.service.services;

import cadsok.order.application.service.mapper.OrderDataMapper;
import cadsok.order.application.service.models.create.CreateOrderCommand;
import cadsok.order.application.service.models.create.CreateOrderResponse;
import cadsok.order.application.service.ports.output.repository.CustomerRepository;
import cadsok.order.application.service.ports.output.repository.OrderRepository;
import cadsok.order.application.service.ports.output.repository.RestaurantRepository;
import cadsok.order.domain.core.entity.Customer;
import cadsok.order.domain.core.entity.Order;
import cadsok.order.domain.core.entity.Restaurant;
import cadsok.order.domain.core.event.OrderCreatedEvent;
import cadsok.order.domain.core.exception.OrderDomainException;
import cadsok.order.domain.core.services.OrderDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
class OrderCreateCommandHandler {

    private final OrderDataMapper orderDomainMapper;
    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    OrderCreateCommandHandler(OrderDataMapper orderDomainMapper,
                              ApplicationDomainEventPublisher applicationDomainEventPublisher,
                              OrderDomainService orderDomainService,
                              OrderRepository orderRepository,
                              CustomerRepository customerRepository,
                              RestaurantRepository restaurantRepository,
                              OrderDataMapper orderDataMapper) {
        this.orderDomainMapper = orderDomainMapper;
        this.applicationDomainEventPublisher = applicationDomainEventPublisher;
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        OrderCreatedEvent orderCreatedEvent = persistOrder(command);
        log.info("Order created with id {}", orderCreatedEvent.getOrder().getId().getValue());
        applicationDomainEventPublisher.publish(orderCreatedEvent);
        CreateOrderResponse createOrderResponse = orderDomainMapper.orderToCreateOrderResponse(
                orderCreatedEvent.getOrder(),
                "Order created successfully"
        );

        // TODO: payment outbox

        log.info("CreateOrderResponse: {}", createOrderResponse);
        return createOrderResponse;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);
        if (optionalRestaurant.isEmpty()) {
            log.warn("Could not find restaurant with restaurant id: {}", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Could not find restaurant with restaurant id: " +
                    createOrderCommand.getRestaurantId());
        }
        return optionalRestaurant.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomerById(customerId);
        if (customer.isEmpty()) {
            log.warn("Could not find customer with customer id: {}", customerId);
            throw new OrderDomainException("Could not find customer with customer id: " + customer);
        }
    }

    private void saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderResult.getId().getValue());
    }
}
