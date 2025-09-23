package commonmodule.domain.events.publisher;

import commonmodule.domain.events.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent<?>> {

    void publish(T event);

}
