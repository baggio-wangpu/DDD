package com.bee.master.adapter.restful.controller;

import com.bee.master.adapter.client.TokenClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Resource
    TokenClient tokenClient;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public void login() {
        tokenClient.login();
    }
}
