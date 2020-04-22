package com.bee.master.application.vo;

import com.bee.master.adapter.jpa.entity.RolePO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleVO {

    private Long id;

    private String title;

    private String description;

    public RoleVO (RolePO entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
    }

    public RoleVO(String title, String desc) {
        this.title = title;
        this.description = desc;
    }
}
