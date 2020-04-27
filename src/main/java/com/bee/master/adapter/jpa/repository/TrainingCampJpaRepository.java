package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingCampJpaRepository extends JpaRepository<TrainingCampPO, String> {
}
