package com.bee.master.adapter.restful.controller;


import com.bee.master.adapter.client.UserClient;
import com.bee.master.application.request.PasswordResetApplyRequest;
import com.bee.master.application.request.PasswordResetRequest;
import com.bee.master.application.request.RegisterRequest;
import com.bee.master.application.vo.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserClient userClient;

    @PostMapping("password-reset-application")
    public void applyPasswordReset(@Valid @RequestBody PasswordResetApplyRequest passwordResetApplyRequest) {
        userClient.applyPasswordReset(passwordResetApplyRequest);
    }

    @PutMapping("{userId}/password")
    public void updatePassword(@PathVariable String userId,
                               @Valid @RequestBody PasswordResetRequest passwordResetRequest) {
        userClient.updatePassword(userId, passwordResetRequest);
    }

    @GetMapping
    public UserVO getUserByResetKey(@RequestParam("key") String resetKey) {
        return userClient.getUserByResetKey(resetKey);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserVO signUp(@Valid @RequestBody RegisterRequest user) {
        return userClient.signUp(user);
    }
}
