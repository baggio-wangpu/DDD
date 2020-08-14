package com.bee.master.adapter.restful.controller;

import com.bee.master.application.vo.TrainingCampVO;
import com.bee.master.domain.trainingcamp.TrainingCamp;
import com.bee.master.domain.trainingcamp.TrainingCampService;
import com.bee.master.infrastructure.configuration.security.AuthHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("camps")
@AllArgsConstructor
public class TrainingCampController {

  private TrainingCampService trainingCampService;
  private AuthHelper authHelper;

  @PostMapping("campCreate")
  public TrainingCamp createCamp(@RequestBody TrainingCampVO trainingCampVO) {
    return trainingCampService.create(trainingCampVO.getName(), trainingCampVO.getClientName(),
        trainingCampVO.getStartTime(), trainingCampVO.getEndTime(), authHelper.getUserId());
  }

  @PostMapping("campUpdate")
  public TrainingCamp updateCamp(@RequestBody TrainingCampVO trainingCampVO) {
    return trainingCampService.update(trainingCampVO.getId(), trainingCampVO.getName(), trainingCampVO.getClientName(),
        trainingCampVO.getStartTime(), trainingCampVO.getEndTime());
  }
}
