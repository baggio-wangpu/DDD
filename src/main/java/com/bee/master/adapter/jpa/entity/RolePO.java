package com.bee.master.adapter.jpa.entity;

import com.bee.master.application.dto.RoleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class RolePO {
    @Id
    @GeneratedValue(generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "com.bee.master.adapter.jpa.SnowflakeIdGenerator")
    private Long id;

    private String title;

    private String description;

    public RolePO(RoleDTO dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
    }

    public RolePO(String title, String desc) {
        this.title = title;
        this.description = desc;
    }
}
