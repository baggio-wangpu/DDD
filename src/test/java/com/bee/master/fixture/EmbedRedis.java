package com.bee.master.fixture;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.embedded.RedisServer;

public class EmbedRedis extends SpringExtension {
    RedisServer redisServer;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        super.beforeAll(context);
        redisServer = RedisServer.builder().setting("bind 127.0.0.1").port(6377).build();
        redisServer.start();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        super.afterAll(context);
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
