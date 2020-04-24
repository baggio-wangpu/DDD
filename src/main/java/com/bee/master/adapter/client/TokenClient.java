package com.bee.master.adapter.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "token", path = "/api")
public interface TokenClient {
    @PostMapping("/tokens")
    String login();
}
