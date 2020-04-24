package com.bee.master.adapter.restful.controller;

import com.bee.master.adapter.client.TokenClient;
import com.bee.master.application.request.LoginRequest;
import com.bee.master.application.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/public")
public class TokenController {

    @Resource
    TokenClient tokenClient;

    @PostMapping(path = "tokens", produces = APPLICATION_JSON_VALUE)
    public LoginVO login(@RequestBody @Valid LoginRequest params) {
        return tokenClient.login(params);
    }
}
