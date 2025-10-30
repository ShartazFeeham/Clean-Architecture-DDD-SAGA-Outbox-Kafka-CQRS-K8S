package cadsok.order.data.adapter;

import cadsok.order.domain.application.ports.output.repository.CustomerRepository;
import cadsok.order.data.entities.CustomerEntity;
import cadsok.order.data.mapper.CustomerMapper;
import cadsok.order.data.repositories.CustomerJpaRepository;
import cadsok.order.domain.core.entity.Customer;
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
