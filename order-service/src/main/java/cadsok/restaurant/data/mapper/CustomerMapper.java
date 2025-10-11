package cadsok.restaurant.data.mapper;

import cadsok.restaurant.data.entities.CustomerEntity;
import cadsok.restaurant.domain.core.entity.Customer;
import commonmodule.domain.values.CustomerId;

public class CustomerMapper {

    public static Customer toCustomer(CustomerEntity customerEntity) {
        return Customer.Builder.builder()
                .id(new CustomerId(customerEntity.getCustomerId()))
                .name(customerEntity.getName())
                .build();
    }

    public static CustomerEntity toCustomerEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customer.getId().getValue());
        customerEntity.setName(customer.getName());
        return customerEntity;
    }
}
