package com.bee.master.application.service.trainingCamp;

import com.bee.master.application.mapper.GenericMapper;
import com.bee.master.application.request.trainingCamp.CreateTrainingCampRequest;
import com.bee.master.application.request.trainingCamp.UpdateTrainingCampRequest;
import com.bee.master.application.vo.TrainingCampVO;
import com.bee.master.domain.trainingcamp.TrainingCamp;
import com.bee.master.domain.trainingcamp.TrainingCampService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainingCampWriteService {

    private final TrainingCampService trainingCampService;
    private final GenericMapper genericMapper;

    public TrainingCampVO create(CreateTrainingCampRequest request, String createUserId) {
        TrainingCamp trainingCamp = trainingCampService.create(request.getName(), request.getClientName(),
                request.getStartTime(), request.getEndTime(), createUserId);
        return genericMapper.trainingCampToVO(trainingCamp);
    }

    public TrainingCampVO update(UpdateTrainingCampRequest request) {
        TrainingCamp trainingCamp = trainingCampService.update(request.getId(), request.getName(), request.getClientName(),
                request.getStartTime(), request.getEndTime());
        return genericMapper.trainingCampToVO(trainingCamp);
    }
}
