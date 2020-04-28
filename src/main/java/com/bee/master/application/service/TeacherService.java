package com.bee.master.application.service;

import com.bee.master.adapter.jpa.entity.TrainerPO;
import com.bee.master.adapter.jpa.repository.TrainerJpaRepository;
import com.bee.master.application.mapper.GenericMapper;
import com.bee.master.application.vo.TrainingCampVO;
import com.bee.master.common.exception.BaseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class TeacherService {

    private final GenericMapper genericMapper;
    private final TrainerJpaRepository trainerJpaRepository;

    @Transactional(readOnly = true)
    public List<TrainingCampVO> getTrainingCampsByTeacher(String userId) {
        return getTeacher(userId)
          .getTrainingCamps().stream()
          .map(genericMapper::trainingCampPOToVO)
          .collect(toList());
    }

    private TrainerPO getTeacher(String userId) {
        return trainerJpaRepository.findById(userId)
          .orElseThrow(() -> BaseException.notFound("User", userId));
    }
}
