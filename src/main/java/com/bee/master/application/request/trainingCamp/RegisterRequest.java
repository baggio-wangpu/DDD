package com.bee.master.application.request.trainingCamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest {

    private static final String PASSWORD_PATTERN = "^((?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,16})$";

    @NotBlank(message = "Real name should not be blank!")
    private String realName;

    @NotBlank(message = "Email should not be blank!")
    private String email;

    @NotBlank(message = "Password should not be blank!")
    @Pattern(message = "invalid password", regexp = PASSWORD_PATTERN)
    private String password;

    private String invitationCode;
}
