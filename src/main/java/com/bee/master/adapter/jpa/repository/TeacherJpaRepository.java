package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.TeacherPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherJpaRepository extends JpaRepository<TeacherPO, String> {
}
