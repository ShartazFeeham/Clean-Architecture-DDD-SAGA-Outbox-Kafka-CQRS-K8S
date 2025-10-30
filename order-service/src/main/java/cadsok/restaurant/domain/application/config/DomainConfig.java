package cadsok.restaurant.domain.application.config;

import cadsok.restaurant.domain.core.services.OrderDomainService;
import cadsok.restaurant.domain.core.services.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for domain layer beans.
 * Keeps domain layer free of Spring annotations while allowing dependency injection.
 */
@Configuration
public class DomainConfig {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
