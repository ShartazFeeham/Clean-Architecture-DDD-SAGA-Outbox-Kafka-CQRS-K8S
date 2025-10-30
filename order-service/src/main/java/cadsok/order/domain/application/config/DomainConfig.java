package cadsok.order.domain.application.config;

import cadsok.order.domain.core.services.OrderDomainService;
import cadsok.order.domain.core.services.OrderDomainServiceImpl;
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
