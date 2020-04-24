package com.bee.master.adapter.restful.controller;


import com.bee.master.adapter.client.TokenClient;
import com.bee.master.application.request.PasswordResetApplyRequest;
import com.bee.master.application.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/public")
public class UserController {

    @Resource
    TokenClient tokenClient;

    @PostMapping
    @ResponseStatus(CREATED)
    public void applyPasswordReset(@Valid @RequestBody PasswordResetApplyRequest request) {
        tokenClient.applyPasswordReset(request);
    }
}
