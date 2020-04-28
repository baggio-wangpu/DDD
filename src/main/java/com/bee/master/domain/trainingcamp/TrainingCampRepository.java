package com.bee.master.domain.trainingcamp;

import java.util.List;

public interface TrainingCampRepository {

    TrainingCamp save(TrainingCamp trainingCamp, String createUserId);

    List<TrainingCamp> findTrainingCampsByUserId(String userId);

    TrainingCamp findById(Long trainingCampId);

    int update(TrainingCamp updateTrainingCamp);
}
