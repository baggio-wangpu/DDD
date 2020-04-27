package com.bee.master.application.service;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import com.bee.master.adapter.jpa.repository.TrainingCampRepository;
import com.bee.master.application.vo.TrainingCampVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class TrainingCampReadService {

    private final TrainingCampRepository trainingCamp;

    public List<TrainingCampVO> getRelatedTrainingCamps(String userId) {
        List<TrainingCampPO> trainingCampPO = findUserRelatedCamps(userId);

        return trainingCampPO.stream()
                .map(this::buildTrainingCampLoadContent)
                .collect(toList());
    }

    private List<TrainingCampPO> findUserRelatedCamps(String userId) {
        return trainingCamp.findTrainingCampsByUserId(userId);
    }

    private TrainingCampVO buildTrainingCampLoadContent(TrainingCampPO trainingCampPO) {
        return TrainingCampVO.buildBy(trainingCampPO);
    }
}
