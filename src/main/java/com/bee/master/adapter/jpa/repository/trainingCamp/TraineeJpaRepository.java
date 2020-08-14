package com.bee.master.adapter.jpa.repository.trainingCamp;

import com.bee.master.adapter.jpa.entity.trainingCamp.TraineePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeJpaRepository extends JpaRepository<TraineePO, String> {
}
