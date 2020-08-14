package com.bee.master.domain.directionalPackage;

import com.bee.master.domain.framework.AggregateRoot;
import com.bee.master.infrastructure.utils.SnowFlake;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AggregateRoot
public class DirectionalPackage {

    private long id;

    private String name;

    private String description;

    public static DirectionalPackage create(String name, String description) {
        return new DirectionalPackage(SnowFlake.nextIdentity(), name, description);
    }

    public static DirectionalPackage fromPO(long id, String name, String description) {
        return new DirectionalPackage(id, name, description);
    }

    public DirectionalPackage update(String name, String description) {
        this.name = name;
        this.description = description;
        return this;
    }
}
