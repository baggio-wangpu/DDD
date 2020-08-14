package com.bee.master.adapter.restful.controller;

import com.bee.master.application.request.trainingCamp.LoginRequest;
import com.bee.master.application.service.trainingCamp.UserReadService;
import com.bee.master.application.vo.LoginVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("tokens")
@AllArgsConstructor
public class RoleController {

    private final UserReadService userReadService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public LoginVO login(@RequestBody @Valid LoginRequest params) {
        return userReadService.login(params);
    }
}
