package com.bee.master.adapter.jpa.repository.directionalPackage;

import com.bee.master.adapter.jpa.entity.directionalPackage.DirectionalPackagePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionalPackageJpaRepository extends JpaRepository<DirectionalPackagePO, Long> {
}
