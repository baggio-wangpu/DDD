package com.bee.master.adapter.restful.controller;


import com.bee.master.adapter.client.UserClient;
import com.bee.master.application.request.PasswordResetApplyRequest;
import com.bee.master.application.request.PasswordResetRequest;
import com.bee.master.application.request.RegisterRequest;
import com.bee.master.application.vo.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserClient userClient;

    @PostMapping("password-reset-application")
    public void applyPasswordReset(@Valid @RequestBody PasswordResetApplyRequest passwordResetApplyRequest, HttpServletRequest request) {
        userClient.applyPasswordReset(passwordResetApplyRequest);
    }

    @PutMapping("{userId}/password")
    public void updatePassword(HttpServletRequest request, @PathVariable String userId,
                               @Valid @RequestBody PasswordResetRequest passwordResetRequest) {
        userClient.updatePassword(userId, passwordResetRequest);
    }

    @GetMapping
    public UserVO getUserByResetKey(@RequestParam("key") String resetKey) {
        return userClient.getUserByResetKey(resetKey);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserVO signUp(@Valid @RequestBody RegisterRequest user, HttpServletRequest request) {
        return userClient.signUp(user);
    }
}
