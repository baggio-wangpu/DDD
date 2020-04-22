package com.bee.master.application.dto;

import com.bee.master.adapter.jpa.entity.RolePO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {
    private Long id;
    private String title;
    private String description;

    public RoleDTO (RolePO entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
    }

    public RoleDTO(String title, String desc) {
        this.title = title;
        this.description = desc;
    }
}
