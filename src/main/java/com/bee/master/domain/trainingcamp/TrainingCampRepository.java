package com.bee.master.domain.trainingcamp;

import com.bee.master.domain.framework.DomainRepository;

import java.util.List;

@DomainRepository
public interface TrainingCampRepository {

    TrainingCamp save(TrainingCamp trainingCamp, String createUserId);

    TrainingCamp findById(Long trainingCampId);

    int update(TrainingCamp updateTrainingCamp);
}
