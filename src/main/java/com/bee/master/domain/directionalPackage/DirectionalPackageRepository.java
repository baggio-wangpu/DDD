package com.bee.master.domain.directionalPackage;

import com.bee.master.domain.framework.DomainRepository;

@DomainRepository
public interface DirectionalPackageRepository {
  DirectionalPackage save(DirectionalPackage directionalPackage);
}
