package com.bee.master.common;

import com.bee.master.fixture.EmbedRedis;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(EmbedRedis.class)
@ActiveProfiles("test")
public abstract class BaseAPITest {
    ObjectMapper mapper = new ObjectMapper();

    @LocalServerPort
    protected int port;

    @Autowired
    private Flyway flyway;

    @Autowired
    protected RedissonClient redissonClient;

    @BeforeEach
    void setup() {
        redissonClient.getKeys().flushall();
//        flyway.clean();
//        flyway.migrate();

    }

    protected String getBaseUrl(String path) {
        return String.format("http://localhost:%s/api/%s", port, path);
    }

    protected String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            return null;
        }
    }
}
