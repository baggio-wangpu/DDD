package com.bee.master.adapter.jpa.repository;

import com.bee.master.adapter.jpa.entity.StudentPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpaRepository extends JpaRepository<StudentPO, String> {
}
