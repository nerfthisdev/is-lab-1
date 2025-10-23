package com.nerfthisdev.islab1.controller;

import com.nerfthisdev.islab1.service.DomainEventPublisher.DomainEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class StreamController {
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping(path = "/api/workers/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter(0L);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        return emitter;
    }

    @EventListener
    public void onDomainEvent(DomainEvent ev) {
        if (!"workers".equals(ev.topic())) return;
        emitters.forEach(em -> {
            try { em.send(SseEmitter.event().data(ev.payload())); }
            catch (IOException ignored) {}
        });
    }
}
