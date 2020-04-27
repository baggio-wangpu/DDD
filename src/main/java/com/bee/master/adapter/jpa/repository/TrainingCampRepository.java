package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import com.bee.master.adapter.jpa.entity.TrainingCampStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingCampRepository extends JpaRepository<TrainingCampPO, String> {

    @Query(value = "select p from UserProductRole as pu left join Product as p on p.id = pu.productId where pu.userId = :userId and p.status <> 'DELETED' ORDER BY p.lastModified DESC ")
    List<TrainingCampPO> findTrainingCampsByUserId(@Param("userId") String userId);

    List<TrainingCampPO> findAllByStatusIsNotOrderByLastModifiedDesc(@Param("status") TrainingCampStatus status);

    @Modifying
    @Query("update Product p set p.status = 'DELETED' where p.id = :productId AND p.status = 'IN_PROGRESS'")
    int setTrainingCampToDeleted(@Param("trainingCampId") String trainingCampId);

    TrainingCampPO findTrainingById(@Param("id") String id);

    Optional<TrainingCampPO> findTrainingByNameAndStatus(@Param("name") String name, @Param("status") TrainingCampStatus status);
}
