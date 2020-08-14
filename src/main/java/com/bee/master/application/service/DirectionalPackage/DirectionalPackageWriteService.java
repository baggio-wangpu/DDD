package com.bee.master.application.service.DirectionalPackage;

import com.bee.master.application.mapper.GenericMapper;
import com.bee.master.application.request.trainingCamp.CreateDirectionalPackageRequest;
import com.bee.master.application.vo.DirectionalPackageVO;
import com.bee.master.domain.directionalPackage.DirectionalPackage;
import com.bee.master.domain.directionalPackage.DirectionalPackageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DirectionalPackageWriteService {

  private final DirectionalPackageService directionalPackageService;
  private final GenericMapper genericMapper;

  public DirectionalPackageVO createDirectionalPackage(CreateDirectionalPackageRequest request) {
    DirectionalPackage directionalPackage = directionalPackageService.create(request.getName(), request.getDescription());
    return genericMapper.directionalPackageToVO(directionalPackage);
  }
}
