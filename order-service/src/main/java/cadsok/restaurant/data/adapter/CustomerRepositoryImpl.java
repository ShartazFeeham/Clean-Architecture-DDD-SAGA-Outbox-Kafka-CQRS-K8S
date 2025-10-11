package cadsok.restaurant.data.adapter;

import cadsok.restaurant.domain.application.ports.output.repository.CustomerRepository;
import cadsok.restaurant.data.entities.CustomerEntity;
import cadsok.restaurant.data.mapper.CustomerMapper;
import cadsok.restaurant.data.repositories.CustomerJpaRepository;
import cadsok.restaurant.domain.core.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Optional<Customer> findCustomerById(UUID customerId) {
        Optional<CustomerEntity> customerOp = customerJpaRepository.findById(customerId);
        if (customerOp.isEmpty()) {
            return Optional.empty();
        }
        return customerOp.map(CustomerMapper::toCustomer);
    }
}
