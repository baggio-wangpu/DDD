package com.bee.master.adapter.client;

import com.bee.master.application.request.trainingCamp.LoginRequest;
import com.bee.master.application.request.trainingCamp.PasswordResetApplyRequest;
import com.bee.master.application.request.trainingCamp.PasswordResetRequest;
import com.bee.master.application.request.trainingCamp.RegisterRequest;
import com.bee.master.application.vo.LoginVO;
import com.bee.master.application.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "business", url = "${feign.business.url}", path = "/api", primary = false)
public interface UserClient {

    @PostMapping(path = "tokens", produces = APPLICATION_JSON_VALUE)
    LoginVO login(@RequestBody @Valid LoginRequest params);


    @PostMapping(path = "/users/password-reset-application")
    void applyPasswordReset(@RequestBody @Valid PasswordResetApplyRequest params);

    @PutMapping("/users/{userId}/password")
    void updatePassword(@PathVariable("userId") String userId,
                        @Valid @RequestBody PasswordResetRequest passwordResetRequest);

    @GetMapping("/users")
    UserVO getUserByResetKey(@RequestParam("key") String resetKey);

    @PostMapping("/users")
    @ResponseStatus(CREATED)
    UserVO signUp(@Valid @RequestBody RegisterRequest user);
}
