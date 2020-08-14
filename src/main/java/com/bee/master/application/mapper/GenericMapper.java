package com.bee.master.application.mapper;

import com.bee.master.adapter.jpa.entity.trainingCamp.TrainingCampPO;
import com.bee.master.application.vo.TrainingCampVO;
import com.bee.master.domain.trainingcamp.TrainingCamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GenericMapper {

    TrainingCampVO trainingCampToVO(TrainingCamp trainingCamp);

    @Mappings({
            @Mapping(target = "createdTime", ignore = true),
            @Mapping(target = "lastModifiedTime", ignore = true),
            @Mapping(target = "trainers", ignore = true)
    })
    TrainingCampPO trainingCampToPO(TrainingCamp trainingCamp);

    TrainingCampVO trainingCampPOToVO(TrainingCampPO trainingCampPO);
}
