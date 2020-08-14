package com.bee.master.domain.directionalPackage;

import com.bee.master.domain.framework.DomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@DomainService
public class DirectionalPackageService {

  private DirectionalPackageRepository directionalPackageRepository;

  @Transactional
  public DirectionalPackage create(String name, String description) {
    DirectionalPackage directionalPackage = DirectionalPackage.create(name, description);
    return directionalPackageRepository.save(directionalPackage);
  }
}
