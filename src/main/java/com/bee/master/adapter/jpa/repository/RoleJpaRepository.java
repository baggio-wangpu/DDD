package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleJpaRepository extends JpaRepository<RolePO, Long> , JpaSpecificationExecutor<RolePO> {

}

