package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.TraineePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeJpaRepository extends JpaRepository<TraineePO, String> {
}
