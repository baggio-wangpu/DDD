package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingCampJpaRepository extends JpaRepository<TrainingCampPO, Long
        > {

    @Query(value = "select c from bm_training_camp_teacher_mapping as tu " +
            "left join bm_training_camp as c on c.id = tu.training_camp_id " +
            "where tu.teacher_id = :userId and c.status = 'ACTIVE' " +
            "ORDER BY c.lastModifiedTime DESC",
            nativeQuery = true)
    List<TrainingCampPO> findTrainingCampsByUserId(@Param("userId") String userId);

    Optional<TrainingCampPO> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bm_training_camp t " +
            "SET t.name = :#{#trainingCampPO.name}, " +
            "    t.client_name = :#{#trainingCampPO.clientName}, " +
            "    t.start_time=:#{#trainingCampPO.startTime}, " +
            "    t.end_time=:#{#trainingCampPO.endTime} " +
            "where t.id = :#{#trainingCampPO.id} " +
            "AND t.status = 'ACTIVE'", nativeQuery = true)
    int update(@Param("trainingCampPO") TrainingCampPO trainingCampPO);

}
