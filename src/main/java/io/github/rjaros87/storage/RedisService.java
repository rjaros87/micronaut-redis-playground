package io.github.rjaros87.storage;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.reactive.ChannelMessage;
import io.lettuce.core.pubsub.api.reactive.RedisPubSubReactiveCommands;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.context.scope.Refreshable;
import io.micronaut.runtime.context.scope.refresh.RefreshEvent;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Refreshable
@Singleton
public class RedisService implements ApplicationEventListener<RefreshEvent>  {
    public final static String REDIS_CHANNEL = "channel";

    private final StatefulRedisConnection<String, String> redisConnection;
    private final StatefulRedisPubSubConnection<String, String> redisSubConnection;
    private final StatefulRedisPubSubConnection<String, String> redisPubConnection;

    private final RedisReactiveCommands<String, String> redis;
    private final RedisPubSubReactiveCommands<String, String> redisSub;
    private final RedisPubSubReactiveCommands<String, String> redisPub;

    public RedisService(
            StatefulRedisConnection<String, String> redisConnection,
            @Named("sub") StatefulRedisPubSubConnection<String, String> redisSubConnection,
            @Named("pub") StatefulRedisPubSubConnection<String, String> redisPubConnection
    ) {
        log.info("RedisService constructor called");

        this.redisConnection = redisConnection;
        this.redisSubConnection = redisSubConnection;
        this.redisPubConnection = redisPubConnection;

        redis = redisConnection.reactive();
        redisSub = redisSubConnection.reactive();
        redisPub = redisPubConnection.reactive();

        redisSub.subscribe(REDIS_CHANNEL).subscribe();
    }

    public Mono<String> put(String key, String value) {
        return redis.set(key, value)
            .doOnSuccess(s -> log.info("Successfully executed set command: {}", s))
            .doOnError(err -> log.error("Unexpected error during set command: {}", err.getMessage()));
    }

    public Mono<String> get(String key) {
        return redis.get(key)
            .doOnSuccess(s -> log.info("Successfully executed get command: {}", s))
            .doOnError(err -> log.error("Unexpected error during get command: {}", err.getMessage()));
    }

    public Mono<Long> publish(String message) {
        return redisPub.publish(REDIS_CHANNEL, message)
            .doOnSuccess(s -> log.info("Successfully executed publish command: {}", s))
            .doOnError(err -> log.error("Unexpected error during publish command: {}", err.getMessage()));
    }

    public Flux<ChannelMessage<String, String>> getRedisServiceSubObserver() {
        return redisSub.observeChannels();
    }

    @Override
    public void onApplicationEvent(RefreshEvent event) {
        log.info("RedisService refresh event! is redis open? '{}', is redisSub open? '{}', is redisPub open? '{}'",
                redisConnection.isOpen(),
                redisSubConnection.isOpen(),
                redisPubConnection.isOpen()
        );

    }
}
