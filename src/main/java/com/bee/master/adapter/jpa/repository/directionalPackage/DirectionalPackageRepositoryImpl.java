package com.bee.master.adapter.jpa.repository.directionalPackage;

import com.bee.master.adapter.jpa.entity.directionalPackage.DirectionalPackagePO;
import com.bee.master.application.mapper.GenericMapper;
import com.bee.master.domain.directionalPackage.DirectionalPackage;
import com.bee.master.domain.directionalPackage.DirectionalPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class DirectionalPackageRepositoryImpl implements DirectionalPackageRepository {

  private final DirectionalPackageJpaRepository directionalPackageJpaRepository;
  private final GenericMapper genericMapper;

  @Override
  public DirectionalPackage save(DirectionalPackage directionalPackage) {
    DirectionalPackagePO directionalPackagePO = directionalPackageJpaRepository.
        save(genericMapper.directionalPackageToPO(directionalPackage));
    return directionalPackagePO.toDomain();
  }
}
