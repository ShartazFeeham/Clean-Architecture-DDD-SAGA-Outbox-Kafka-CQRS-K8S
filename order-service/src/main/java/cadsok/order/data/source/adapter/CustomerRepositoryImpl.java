package cadsok.order.data.source.adapter;

import cadsok.order.application.service.ports.output.repository.CustomerRepository;
import cadsok.order.data.source.entities.CustomerEntity;
import cadsok.order.data.source.mapper.CustomerMapper;
import cadsok.order.data.source.repositories.CustomerJpaRepository;
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
