package com.bee.master.domain.trainingcamp;

import com.bee.master.common.exception.BaseException;
import com.bee.master.common.ddd.framework.DomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@AllArgsConstructor
@DomainService
public class TrainingCampService {

    private final TrainingCampRepository trainingCampRepository;

    @Transactional
    public TrainingCamp create(String name, String clientName, LocalDateTime startTime, LocalDateTime endTime, String createUserId) {
        TrainingCamp newTrainingCamp = TrainingCamp.create(name, clientName, startTime, endTime);
        return trainingCampRepository.save(newTrainingCamp, createUserId);
    }

    public TrainingCamp update(Long id, String name, String clientName, LocalDateTime startTime, LocalDateTime endTime) {
        TrainingCamp existTrainingCamp = trainingCampRepository.findById(id);
        if (!existTrainingCamp.editAble()) {
            throw BaseException.badRequest(format("Training camp %s is not active", id));
        }
        TrainingCamp updateTrainingCamp = existTrainingCamp.update(name, clientName, startTime, endTime);
        int updateRow = trainingCampRepository.update(updateTrainingCamp);
        if (updateSuccess(updateRow)) {
            return updateTrainingCamp;
        }
        throw BaseException.badRequest(format("Training camp %s is not active", id));
    }

    private boolean updateSuccess(int updateRow) {
        return updateRow != 0;
    }
}
