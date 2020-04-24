package com.bee.master.application.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordResetApplyRequest {
    @NotBlank(message = "email should not be blank!")
    private String email;
}
