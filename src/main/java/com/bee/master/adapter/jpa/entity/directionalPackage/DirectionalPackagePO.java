package com.bee.master.adapter.jpa.entity.directionalPackage;

import com.bee.master.domain.directionalPackage.DirectionalPackage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bm_directional_package")
public class DirectionalPackagePO {

    @Id
    private long id;

    @NotNull
    private String name;

    private String description;

    public DirectionalPackage toDomain() {
        return DirectionalPackage.fromPO(this.id, this.name, this.description);
    }
}
