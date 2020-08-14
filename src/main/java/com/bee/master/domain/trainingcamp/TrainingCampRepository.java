package com.bee.master.domain.trainingcamp;

import com.bee.master.common.ddd.framework.DomainRepository;

@DomainRepository
public interface TrainingCampRepository {

    TrainingCamp save(TrainingCamp trainingCamp, String createUserId);

    int update(TrainingCamp updateTrainingCamp);

    TrainingCamp findById(Long trainingCampId);
}
