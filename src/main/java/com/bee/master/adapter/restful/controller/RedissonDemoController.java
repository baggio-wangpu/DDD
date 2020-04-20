package com.bee.master.adapter.restful.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("public")
public class RedissonDemoController {

    private final RedissonClient redissonClient;

    public RedissonDemoController(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @PostMapping(path = "keys", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void put(@RequestBody KeyValuePair keyValuePair) {
        RBucket<String> keyObj = redissonClient.getBucket(keyValuePair.getKey());
        keyObj.set(keyValuePair.getValue());
    }

    @GetMapping("keys/{key}")
    public KeyValuePair get(@PathVariable String key) {
        return new KeyValuePair(key, redissonClient.getBucket(key).get().toString());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class KeyValuePair {
        private String key;
        private String value;
    }

}
