package io.github.rjaros87.controller;

import io.github.rjaros87.storage.RedisService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class RedisController {
    private final RedisService redisService;

    RedisController(RedisService redisService) {
        this.redisService = redisService;

        registerObserver();
    }

    @Get("/{key}")
    public Mono<String> getRedisKey(@PathVariable String key) {
        return redisService.get(key);
    }

    @Put(value = "/{key}", consumes = MediaType.ALL)
    public Mono<String> putRedisKey(@PathVariable String key, @Body String text) {
        return redisService.put(key, text);
    }

    @Post(consumes = MediaType.ALL)
    public Mono<Long> publishRedisMessage(@Body String text) {
        return redisService.publish(text);
    }

    private void registerObserver() {
        log.info("Going to subscribe RedisSubObserver");

        redisService.getRedisServiceSubObserver().subscribe(
            message -> log.info("Got message from RedisService publisher: {}", message.getMessage()),
            err -> log.error("Unexpected error in getRedisServiceSubObserver subscriber: {}", err.getMessage())
        );
    }
}
