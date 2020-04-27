package com.bee.master.application.vo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserVO {
    private String id;

    @Deprecated
    private String username;

    private String email;

    private String realName;

    private String city;

    private String company;

    private String position;

    private String avatar;

    private boolean enabled;

    private List<String> authorities;
}
