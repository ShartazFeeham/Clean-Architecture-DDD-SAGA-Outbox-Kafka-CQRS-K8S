package cadsok.payment.gateway;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class PaymentGatewaySimulator {
    // Returns true ~70% of the time
    public boolean succeed() {
        return ThreadLocalRandom.current().nextInt(100) < 70;
    }
    // Returns true ~70% of the time
    public boolean rollbackPayment() {
        return ThreadLocalRandom.current().nextInt(100) < 70;
    }
}
