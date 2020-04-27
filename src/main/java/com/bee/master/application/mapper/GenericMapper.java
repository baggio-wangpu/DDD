package com.bee.master.application.mapper;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import com.bee.master.application.vo.TrainingCampVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenericMapper {
  TrainingCampVO poToVO(TrainingCampPO trainingCampPO);
}
