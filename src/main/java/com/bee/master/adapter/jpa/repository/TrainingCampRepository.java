package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingCampRepository extends JpaRepository<TrainingCampPO, String> {

    @Query(value = "select c from bm_training_camp_teacher_mapping as tu left join bm_training_camp as c on c.id = tu.training_camp_id where tu.teacher_id = :userId and c.status = 'ACTIVE' ORDER BY c.lastModifiedTime DESC ")
    List<TrainingCampPO> findTrainingCampsByUserId(@Param("userId") String userId);
}
