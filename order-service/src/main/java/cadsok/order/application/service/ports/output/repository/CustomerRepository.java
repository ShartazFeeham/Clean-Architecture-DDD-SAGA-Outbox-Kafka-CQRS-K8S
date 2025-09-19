package cadsok.order.application.service.ports.output.repository;

import cadsok.order.domain.core.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomerById(UUID customerId);

}
