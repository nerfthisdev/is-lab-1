package com.nerfthisdev.islab1.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisher {
    private final ApplicationEventPublisher publisher;
    public DomainEventPublisher(ApplicationEventPublisher publisher) { this.publisher = publisher; }

    public void emit(String topic, Object payload) {
        publisher.publishEvent(new DomainEvent(topic, payload));
    }

    public record DomainEvent(String topic, Object payload) {}
}
