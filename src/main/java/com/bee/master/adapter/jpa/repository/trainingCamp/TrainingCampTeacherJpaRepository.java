package com.bee.master.adapter.jpa.repository.trainingCamp;

import com.bee.master.adapter.jpa.entity.trainingCamp.TrainingCampTeacherPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingCampTeacherJpaRepository extends JpaRepository<TrainingCampTeacherPO, Long> {
    Optional<TrainingCampTeacherPO> findByTeacherId(String teacherId);
}
