package cadsok.common.domain.events.publisher;

import cadsok.common.domain.events.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent<?>> {

    void publish(T event);

}
