package com.bee.master.adapter.restful.controller;

import com.bee.master.application.vo.DirectionalPackageVO;
import com.bee.master.domain.directionalPackage.DirectionalPackage;
import com.bee.master.domain.directionalPackage.DirectionalPackageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("directionalPackage")
@AllArgsConstructor
public class DirectionalPackageController {

  private DirectionalPackageService directionalPackageService;

  @PostMapping("create")
  public DirectionalPackage create(@RequestBody DirectionalPackageVO directionalPackageVO) {
    return directionalPackageService.create(directionalPackageVO.getName(), directionalPackageVO.getDescription());
  }
}
