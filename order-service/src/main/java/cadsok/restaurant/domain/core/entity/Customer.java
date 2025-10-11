package cadsok.restaurant.domain.core.entity;

import commonmodule.domain.entity.AggregateRoot;
import commonmodule.domain.values.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    private String name;

    private Customer(Builder builder) {
        super.setId(builder.customerId);
        setName(builder.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer(String name) {
        this.name = name;
    }


    public static final class Builder {
        private CustomerId customerId;
        private String name;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
