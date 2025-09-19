package cadsok.order.application.service.services.input;

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
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
class OrderCreateCommandHandler {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderDataMapper orderDomainMapper;
    private final RestaurantRepository restaurantRepository;

    OrderCreateCommandHandler(OrderDomainService orderDomainService,
                              OrderRepository orderRepository,
                              CustomerRepository customerRepository,
                              OrderDataMapper orderDomainMapper,
                              RestaurantRepository restaurantRepository) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderDomainMapper = orderDomainMapper;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        validateCustomer(command.getCustomerId());
        Restaurant restaurant = validateRestaurant(command);
        Order order = orderDomainMapper.createOrderCommandToOrder(command);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        Order savedOrder = saveOrder(order);
        log.info("Order with id {} created", savedOrder.getId().getValue());
        return orderDomainMapper.orderToCreateOrderResponse(savedOrder, "Order created successfully");
    }

    private Restaurant validateRestaurant(CreateOrderCommand command) {
        Restaurant restaurant = orderDomainMapper.createOrderCommandToRestaurant(command);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);
        optionalRestaurant.ifPresent(r -> log.info("Restaurant with id {} found", command.getRestaurantId()));
        return optionalRestaurant.orElseThrow(() -> {
            log.warn("Restaurant with id {} not found", command.getRestaurantId());
            return new OrderDomainException("Restaurant with id " + command.getRestaurantId() + " not found");
        });
    }

    private void validateCustomer(@NotNull UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomerById(customerId);
        customer.ifPresent(c -> log.info("Customer with id {} found", customerId));
        customer.orElseThrow(() -> {
            log.warn("Customer with id {} not found", customerId);
            return new OrderDomainException("Customer with id " + customerId + " not found");
        });
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order");
            throw new OrderDomainException("Could not save order");
        }
        log.info("Order with id {} saved", orderResult.getId().getValue());
        return orderResult;
    }
}
