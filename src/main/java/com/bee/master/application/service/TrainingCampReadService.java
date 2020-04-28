package com.bee.master.application.service;

import com.bee.master.application.mapper.GenericMapper;
import com.bee.master.application.vo.TrainingCampVO;
import com.bee.master.domain.trainingcamp.TrainingCampRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainingCampReadService {

    private GenericMapper genericMapper;
    private final TrainingCampRepository trainingCampRepository;

    public TrainingCampVO get(Long trainingCampId) {
        return genericMapper.trainingCampToVO(trainingCampRepository.findById(trainingCampId));
    }
}
