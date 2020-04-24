package com.bee.master.adapter.client;

import com.bee.master.application.request.LoginRequest;
import com.bee.master.application.request.PasswordResetApplyRequest;
import com.bee.master.application.vo.LoginVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(value = "token", path = "/api")
public interface TokenClient {

    @PostMapping(path = "tokens", produces = APPLICATION_JSON_VALUE)
    public LoginVO login(@RequestBody @Valid LoginRequest params);


    @PostMapping(path = "/users/password-reset-application")
    public void applyPasswordReset(@RequestBody @Valid PasswordResetApplyRequest params);
}
