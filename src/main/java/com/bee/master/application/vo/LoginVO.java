package com.bee.master.application.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginVO {
    private String token;

    private UserVO userInfo;

    public void setRoles(String roles) {
        this.getUserInfo().setRoles(roles);
    }
}
