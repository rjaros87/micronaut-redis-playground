package io.github.rjaros87.listener;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.crac.events.AfterRestoreEvent;
import io.micronaut.runtime.context.scope.refresh.RefreshEvent;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
@Singleton
public class AfterRestoreEventListener implements ApplicationEventListener<AfterRestoreEvent> {
    private final ApplicationEventPublisher<RefreshEvent> eventPublisher;

    public AfterRestoreEventListener(ApplicationEventPublisher<RefreshEvent> eventPublisher) {
        log.info("AfterRestoreEventListener constructor called");
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onApplicationEvent(AfterRestoreEvent event) {
        eventPublisher.publishEvent(new RefreshEvent());

        log.info("AfterRestoreEventListener time taken: {}", Duration.ofNanos(event.getTimeTakenNanos())
            .toSeconds());
    }
}
