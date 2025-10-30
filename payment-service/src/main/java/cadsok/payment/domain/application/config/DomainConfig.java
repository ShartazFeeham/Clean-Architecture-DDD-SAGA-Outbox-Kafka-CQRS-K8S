package cadsok.payment.domain.application.config;


import cadsok.payment.domain.core.services.PaymentDomainService;
import cadsok.payment.domain.core.services.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for domain layer beans.
 * Keeps domain layer free of Spring annotations while allowing dependency injection.
 */
@Configuration
public class DomainConfig {

    @Bean
    public PaymentDomainService orderDomainService() {
        return new PaymentDomainServiceImpl();
    }
}
