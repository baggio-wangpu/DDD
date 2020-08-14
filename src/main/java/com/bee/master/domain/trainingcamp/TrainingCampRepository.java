package com.bee.master.domain.trainingcamp;

import com.bee.master.common.ddd.framework.DomainRepository;

@DomainRepository
public interface TrainingCampRepository {

    TrainingCamp save(TrainingCamp trainingCamp, String createUserId);

    TrainingCamp findById(Long trainingCampId);

    int update(TrainingCamp updateTrainingCamp);
}
