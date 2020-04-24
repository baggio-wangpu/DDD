package com.bee.master.application.request;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PasswordResetApplyRequest {
    @NotBlank(message = "email should not be blank!")
    private String email;
}
