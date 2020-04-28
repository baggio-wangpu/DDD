package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.TrainerPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerJpaRepository extends JpaRepository<TrainerPO, String> {
}
