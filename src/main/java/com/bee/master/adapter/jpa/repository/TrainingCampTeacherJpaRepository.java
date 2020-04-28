package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.TrainingCampTeacherPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingCampTeacherJpaRepository extends JpaRepository<TrainingCampTeacherPO, Long> {
    Optional<TrainingCampTeacherPO> findByTeacherId(String teacherId);
}
