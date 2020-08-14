package com.bee.master.application.request.trainingCamp;

import com.bee.master.common.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetRequest {

    public static final String PATTERN = "^((?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,16})$";

    private String oldPassword;

    @NotBlank(message = "new password should not be blank!")
    @Pattern(message = "invalid password", regexp = PATTERN)
    private String newPassword;

    private boolean forgetPassword;

    public void validate() {
        if (!forgetPassword && isEmpty(oldPassword)) {
            throw BaseException.badRequest("old_password_should_not_be_blank!");
        }

        if (!forgetPassword && oldPassword.equals(newPassword)) {
            throw BaseException.badRequest("new_password_should_not_as_same_as_old");
        }
    }
}
