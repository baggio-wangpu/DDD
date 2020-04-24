package com.bee.master.adapter.client;

import com.bee.master.application.request.LoginRequest;
import com.bee.master.application.request.PasswordResetApplyRequest;
import com.bee.master.application.request.PasswordResetRequest;
import com.bee.master.application.request.RegisterRequest;
import com.bee.master.application.vo.LoginVO;
import com.bee.master.application.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(value = "token", path = "/api")
public interface TokenClient {

    @PostMapping(path = "tokens", produces = APPLICATION_JSON_VALUE)
    public LoginVO login(@RequestBody @Valid LoginRequest params);


    @PostMapping(path = "/users/password-reset-application")
    public void applyPasswordReset(@RequestBody @Valid PasswordResetApplyRequest params);

    @PutMapping("/users/{userId}/password")
    public void updatePassword(@PathVariable String userId,
                               @Valid @RequestBody PasswordResetRequest passwordResetRequest);

    @GetMapping("/users")
    public UserVO getUserByResetKey(@RequestParam("key") String resetKey);

    @PostMapping
    @ResponseStatus(CREATED)
    public UserVO signUp(@Valid @RequestBody RegisterRequest user);
}
