package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import com.bee.master.adapter.jpa.entity.TrainingCampTeacherPO;
import com.bee.master.application.mapper.GenericMapper;
import com.bee.master.domain.trainingcamp.TrainingCamp;
import com.bee.master.domain.trainingcamp.TrainingCampRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Repository
@AllArgsConstructor
public class TrainingCampRepositoryImpl implements TrainingCampRepository {

    private final TrainingCampJpaRepository trainingCampJpaRepository;
    private final TrainingCampTeacherJpaRepository trainingCampTeacherJpaRepository;
    private final GenericMapper genericMapper;

    @Override
    public TrainingCamp save(TrainingCamp trainingCamp, String createUserId) {
        TrainingCampPO trainingCampPO = trainingCampJpaRepository.save(genericMapper.trainingCampToPO(trainingCamp));
        saveTrainingCampTeacher(createUserId, trainingCampPO);
        return genericMapper.trainingCampPOToDomain(trainingCampPO);
    }

    @Override
    public List<TrainingCamp> findTrainingCampsByUserId(String userId) {
        List<TrainingCampPO> trainingCampPOS = trainingCampJpaRepository.findTrainingCampsByUserId(userId);
        return trainingCampPOS.stream().map(genericMapper::trainingCampPOToDomain).collect(toList());
    }

    @Override
    public TrainingCamp findById(Long trainingCampId) {
        Optional<TrainingCampPO> optionalTrainingCampPO = trainingCampJpaRepository.findById(trainingCampId);
        TrainingCampPO trainingCampPO = optionalTrainingCampPO.orElseThrow(()
                -> new EntityNotFoundException(format("Training camp could not found for %s", trainingCampId)));
        return genericMapper.trainingCampPOToDomain(trainingCampPO);
    }

    @Override
    public int update(TrainingCamp trainingCamp) {
        return trainingCampJpaRepository.update(genericMapper.trainingCampToPO(trainingCamp));
    }

    private void saveTrainingCampTeacher(String createUserId, TrainingCampPO trainingCampPO) {
        TrainingCampTeacherPO trainingCampTeacherPO = TrainingCampTeacherPO.builder()
                .trainingCampId(trainingCampPO.getId())
                .teacherId(createUserId)
                .build();
        trainingCampTeacherJpaRepository.save(trainingCampTeacherPO);
    }
}
